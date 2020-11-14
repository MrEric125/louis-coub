BEGIN
    -- 业务逻辑：自动创建表分区

    # 定义变量
    DECLARE s_table_name VARCHAR(100); -- 表名称
DECLARE i_partition_type TINYINT; -- 分区类型（1：天，2：月）
DECLARE i_after_offset INT; -- 后偏移

DECLARE b_nextDataFlag INT DEFAULT TRUE; -- 游标下一行数据标记，默认：有数据
DECLARE i_index INT DEFAULT 0; -- 循环下标
DECLARE s_p_name VARCHAR(100);-- 分区名称
DECLARE i_p_count INT; -- 分区数量

	# 声明游标
DECLARE cur CURSOR FOR
SELECT table_name,partition_type,after_offset
FROM ts_table_partition_config
WHERE delete_flag=0 AND add_partition_flag=1
ORDER BY partition_type;

#声明异常处理
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET b_nextDataFlag=FALSE;-- 游标无结果
DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
INSERT INTO tb_procedure_exception_log(module_code,exception_info) VALUES('proc_add_table_partition','exec fail');
END;

	# 打开游标
OPEN cur;
		#获取游标当前指针的记录，读取一行数据
FETCH cur into s_table_name,i_partition_type,i_after_offset;
		#开始循环，判断是否游标已经到达了最后作为循环条件
WHILE b_nextDataFlag DO
UPDATE ts_table_partition_config SET add_partition_begin_tm = NOW() WHERE table_name=s_table_name;
SET i_index=0;
IF 1=i_partition_type THEN #按天分区
				WHILE i_index <= i_after_offset DO
#设置分区名称：pyyyymmdd
SET s_p_name=CONCAT('p',date_format(date_add(curdate(),interval i_index day),'%Y%m%d'));
#判断分区是否存在，不存在就新增
SELECT COUNT(1) INTO i_p_count FROM INFORMATION_SCHEMA.PARTITIONS
WHERE table_schema=database() and table_name=s_table_name and partition_name=s_p_name;
IF 0=i_p_count THEN
SET @v_sql=CONCAT('ALTER TABLE ',s_table_name,' ADD PARTITION (PARTITION ',s_p_name,' VALUES LESS THAN ("',date_add(curdate(),interval i_index+1 DAY),'"))');
PREPARE stmt FROM @v_sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END IF;
SET i_index=i_index+1;
END WHILE;
END IF;
IF 2=i_partition_type THEN #按月分区
				WHILE i_index <= i_after_offset DO
SET s_p_name=CONCAT('p',date_format(date_add(curdate(),interval i_index MONTH),'%Y%m'));
SELECT COUNT(1) INTO i_p_count FROM INFORMATION_SCHEMA.PARTITIONS
WHERE table_schema=database() and table_name=s_table_name and partition_name=s_p_name;
IF 0=i_p_count THEN
SET @v_sql=CONCAT('ALTER TABLE ',s_table_name,' ADD PARTITION (PARTITION ',s_p_name,' VALUES LESS THAN ("',date_add(date_add(curdate(),interval -DAY(curdate())+1 DAY),interval i_index+1 MONTH),'"))');
PREPARE stmt FROM @v_sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END IF;
SET i_index=i_index+1;
END WHILE;
END IF;
UPDATE ts_table_partition_config SET add_partition_end_tm = NOW() WHERE table_name=s_table_name;
# 读取下一行数据
FETCH cur into s_table_name,i_partition_type,i_after_offset;
END WHILE;
	#关闭游标
CLOSE cur;
END