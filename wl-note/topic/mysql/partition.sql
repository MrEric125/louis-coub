ALTER TABLE table_a PARTITION by RANGE COLUMNS(OP_TM)
(PARTITION p20191112 VALUES LESS THAN ('2020-01-09') ENGINE = InnoDB,
 PARTITION p20191113 VALUES LESS THAN ('2020-01-10') ENGINE = InnoDB,
 PARTITION p20191114 VALUES LESS THAN ('2020-01-11') ENGINE = InnoDB,
 PARTITION p20191115 VALUES LESS THAN ('2020-01-12') ENGINE = InnoDB,
 PARTITION p20191116 VALUES LESS THAN ('2020-01-13') ENGINE = InnoDB,
 PARTITION p20191117 VALUES LESS THAN ('2020-01-14') ENGINE = InnoDB,
 PARTITION p20191118 VALUES LESS THAN ('2020-01-15') ENGINE = InnoDB,
 PARTITION p20191119 VALUES LESS THAN ('2020-01-16') ENGINE = InnoDB,
 PARTITION p20191120 VALUES LESS THAN ('2020-01-17') ENGINE = InnoDB,
 PARTITION p20191121 VALUES LESS THAN ('2020-01-18') ENGINE = InnoDB,
 PARTITION p20191122 VALUES LESS THAN ('2020-01-19') ENGINE = InnoDB,
 PARTITION p20191123 VALUES LESS THAN ('2020-01-20') ENGINE = InnoDB,
 PARTITION p20191124 VALUES LESS THAN ('2020-01-21') ENGINE = InnoDB,
 PARTITION p20191125 VALUES LESS THAN ('2020-01-22') ENGINE = InnoDB,
 PARTITION p20191126 VALUES LESS THAN ('2020-01-23') ENGINE = InnoDB,
 PARTITION p20191127 VALUES LESS THAN ('2020-01-24') ENGINE = InnoDB,
 PARTITION p20191128 VALUES LESS THAN ('2020-01-25') ENGINE = InnoDB,
 PARTITION p20191129 VALUES LESS THAN ('2020-01-26') ENGINE = InnoDB)