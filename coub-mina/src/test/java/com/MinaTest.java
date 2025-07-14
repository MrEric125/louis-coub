package com;


import com.louis.MinaProject;
import com.mock.OrderCreate;
import com.mock.OrderHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;


/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Slf4j
@Setter
@Getter
@SpringBootTest(classes = MinaProject.class)
public class MinaTest  {


    @Mock
    private OrderCreate orderCreate;

    @Mock
    private OrderHelper orderHelper;

    /**
     * junit 5 需要在测试类中添加 @BeforeEach 注解的方法来初始化 Mockito 的注入
     */
    @BeforeEach
    public  void initMock() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void mockTest(){
        log.info("mockTest");

        when(orderCreate.create()).thenReturn(100.0);
    }
}
