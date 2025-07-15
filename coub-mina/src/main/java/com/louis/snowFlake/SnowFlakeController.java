package com.louis.snowFlake;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SnowFlakeController {

    @Autowired
    private SnowFlack1Repository snowFlack1Repository;

    @Autowired
    private SnowFlack2Repository snowFlack2Repository;

    @Autowired
    private ErrorMessageRepository errorMessageRepository;

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    UniqueIdWorker uniqueIdWorker;

    //    @Scheduled(fixedRate = 30)
    public void unique1Worker() {
        long id = uniqueIdWorker.nextId(RandomUtils.nextLong(0, 7), RandomUtils.nextLong(0, 1));
        try {
            SnowFlack1 snowFlack1 = new SnowFlack1();
            snowFlack1.setId(id);
            snowFlack1Repository.save(snowFlack1);
        } catch (Exception e) {
            taskExecutor.execute(() -> {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setTableName("snowFlack1");
                errorMessage.setTableId(id);
                errorMessageRepository.save(errorMessage);
            });
        }
        log.info("save success table1:{}", id);
    }

    //    @Scheduled(fixedRate = 30)
    public void unique2Worker() {
        long id = uniqueIdWorker.nextId(RandomUtils.nextLong(0, 8), RandomUtils.nextLong(0, 4));
        try {
            SnowFlack2 snowFlack1 = new SnowFlack2();
            snowFlack1.setId(id);
            snowFlack2Repository.save(snowFlack1);
        } catch (Exception e) {
            taskExecutor.execute(() -> {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setTableName("snowFlack1");
                errorMessage.setTableId(id);
                errorMessageRepository.save(errorMessage);
            });
        }
        log.info("save success table2:{}", id);
    }
}
