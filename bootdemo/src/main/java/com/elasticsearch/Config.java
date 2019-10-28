package com.elasticsearch;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author louis
 * <p>
 * Date: 2019/10/23
 * Description:
 */
@Configuration
@EnableElasticsearchRepositories
public class Config {

    @Bean
    Client client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        return client;
    }




}
class Search{

    @Autowired
    Client client;

    public void search() {
        Object object = null;
        IndexRequest request = new IndexRequest("", "", "")
                .source(object)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

        IndexResponse response = client.index(request).actionGet();

    }
}
