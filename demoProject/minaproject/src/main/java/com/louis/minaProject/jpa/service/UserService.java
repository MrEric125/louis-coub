package com.louis.minaProject.jpa.service;

import com.louis.minaProject.jpa.repository2.UserInfoRepository;
import com.louis.snowFlake.ErrorMessage;
import com.louis.snowFlake.ErrorMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Objects;

/**
 * @author louis
 * @date 2023/6/8
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserService self;

    @Autowired
    ErrorMessageRepository errorMessageRepository;

    @Transactional(rollbackFor = Exception.class)
    public void tx1() {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTableName("tx1");
        errorMessage.setTableId(1L);

        errorMessageRepository.save(errorMessage);

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    super.afterCommit();
                    // 异步的发送mq
                    log.info("insert tx1 afterCommit");

                }
            });
        } else {
            log.info("insert tx1 no transaction");
        }

        log.info("insert tx1");

        self.tx1_1();

        self.tx2();
    }


    @Transactional(rollbackFor = Exception.class)
    public void tx2() {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTableName("tx2");
        errorMessage.setTableId(2L);

        errorMessageRepository.save(errorMessage);

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    super.afterCommit();
                    // 异步的发送mq
                    log.info("insert tx2 afterCommit");

                }
            });
        } else {
            log.info("insert tx2 no transaction");
        }

        log.info("insert tx2");

        self.tx2_1();
    }


    @Transactional(rollbackFor = Exception.class)
    public void tx2_1() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTableName("tx2_1");
        errorMessage.setTableId(21L);

        errorMessageRepository.save(errorMessage);

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    super.afterCommit();
                    // 异步的发送mq
                    log.info("insert tx2_1 afterCommit");

                }
            });
        } else {
            log.info("insert tx2_1 no transaction");
        }
        if (Objects.nonNull(errorMessage)) {
            throw new RuntimeException();
        }

        log.info("insert tx2_1");
    }
    @Transactional(rollbackFor = Exception.class)
    public void tx1_1() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTableName("tx1_1");
        errorMessage.setTableId(11L);

        errorMessageRepository.save(errorMessage);

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    super.afterCommit();
                    // 异步的发送mq
                    log.info("insert tx1_1 afterCommit");

                }
            });
        } else {
            log.info("insert tx1_1 no transaction");
        }

        log.info("insert tx1_1");

    }


}
