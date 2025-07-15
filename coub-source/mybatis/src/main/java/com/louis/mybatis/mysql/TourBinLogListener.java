package com.louis.mybatis.mysql;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 乐游监听器
 * SpringBoot启动成功后的执行业务线程操作
 * CommandLineRunner去实现此操作
 * 在有多个可被执行的业务时，通过使用 @Order 注解，设置各个线程的启动顺序（value值由小到大表示启动顺序）。
 * 多个实现CommandLineRunner接口的类必须要设置启动顺序，不让程序启动会报错！
 * <p>
 * <p>
 * 监听的库,表
 * 时间类型的处理，比如查询的处理，插入的处理，都拆分下来
 *
 * @author zrj
 * @since 2021/7/27
 **/
@Slf4j
@Component
@Order(value = 1)
public class TourBinLogListener implements CommandLineRunner {

    @Resource
    private BinLogConstants binLogConstants;

    @Autowired
    private DMLEvent dmlEvent;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化配置信息：" + binLogConstants.toString());

        // 初始化配置信息
        Conf conf = new Conf(binLogConstants.getHost(), binLogConstants.getPort(), binLogConstants.getUsername(), binLogConstants.getPassword());

        Set<EventType> eventType = Sets.newHashSet();
        eventType.addAll(BinLogConstants.updateType);
        eventType.addAll(BinLogConstants.writeType);
        eventType.addAll(BinLogConstants.deleteType);

        BlockingQueue<BinLogItem> blockingQueue = new ArrayBlockingQueue<>(1024);

        Multimap<String, BinLogListener> multimap = ArrayListMultimap.create();

        MysqlBinLogEventListener mysqlBinLogEventListener = new MysqlBinLogEventListener(conf, eventType, blockingQueue, multimap);

        // 获取table集合
        List<String> tableList = BinLogUtils.getListByStr(binLogConstants.getTable());
        if (CollectionUtils.isEmpty(tableList)) {
            return;
        }
        // 注册监听
        tableList.forEach(table -> {
            log.info("注册监听信息，注册DB：" + binLogConstants.getSchema() + "，注册表：" + table);
            try {
                //todo  这里可以新增自定义事件,来处理 监听到的binlog 事件
                mysqlBinLogEventListener.regListener(binLogConstants.getSchema(), table, dmlEvent);
            } catch (Exception e) {
                log.error("BinLog监听异常：", e);
            }
        });
        // 初始化监听器
        BinlogEventCustomer mysqlBinLogListener = new BinlogEventCustomer(conf, mysqlBinLogEventListener, blockingQueue, multimap);
        // 多线程消费
//        mysqlBinLogListener.parse();
    }
}

