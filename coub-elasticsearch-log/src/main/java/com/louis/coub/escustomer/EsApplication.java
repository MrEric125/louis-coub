package com.louis.coub.escustomer;


import com.louis.common.common.HttpResult;
import com.louis.coub.escustomer.registry.EnableEsModel;
import org.elasticsearch.client.ClusterClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableEsModel(entityPath = "com.louis.coub.escustomer.model")
public class EsApplication {

    @Autowired
    private RestHighLevelClient client;



    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

    @RequestMapping("in")
    public HttpResult httpResult() {
        ClusterClient settings = client.cluster();
        return HttpResult.ok(settings.toString());
    }
}
