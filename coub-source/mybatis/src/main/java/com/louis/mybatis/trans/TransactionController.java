package com.louis.mybatis.trans;


import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/trans")
@RestController
public class TransactionController implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @return
     * @see DateSourceTransactionManager
     */
    @RequestMapping("/getBean")
    public HttpResult getBean(String beanName) {

        Object bean = this.applicationContext.getBean(beanName);

        return HttpResult.ok(JSON.toJSONString(null, true));
    }

    @RequestMapping("/insert")
    public HttpResult mybatisTrans() {
        return null;
    }


}
