package com.louis.coub.escustomer.service;

import lombok.extern.slf4j.Slf4j;
import com.louis.coub.escustomer.registry.EsModel;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jun.liu
 * @since 2021/2/4 10:34
 */
@Slf4j
@Component
public class InitIndexService  implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Autowired
    private IndexServiceImpl indexService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beansWithAnnotationMap = this.applicationContext.getBeansWithAnnotation(EsModel.class);
        beansWithAnnotationMap.forEach((beanName,bean) ->
                {
                    try {
                        String templatePath = bean.getClass().getAnnotation(EsModel.class).template();
                        String indexName = bean.getClass().getAnnotation(EsModel.class).index();

                        if (!indexService.existIndex(indexName)) {
                            Resource resource = new ClassPathResource(templatePath);
                            byte[] template = IOUtils.toByteArray(resource.getInputStream());
                            String json = new String(template);
                            indexService.createIndex(indexName, json);
                            log.info("create index :{} success", indexName);
                        }
                        log.info("init index:{} success", indexName);
                    } catch (Exception e) {
                        log.error("init index error",e);
                    }
                }
        );

    }
}
