package com.louis.coub.escustomer.config;

import com.louis.coub.escustomer.exception.CommonError;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(EsProperties.class)
public class EsConfig implements ApplicationContextAware {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContext applicationContext() {
        return applicationContext;
    }


    @Autowired
    private  EsProperties esProperties;


    private List<HttpHost> httpHosts = new ArrayList<>();


//    @Bean
//    @ConditionalOnMissingBean
//    public IndicesClient indicesClient(RestHighLevelClient restHighLevelClient) {
//        return restHighLevelClient.indices();
//    }

//    @Bean
//    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient() {

        List<String> clusterNodes = esProperties.getClusterNodes();
        clusterNodes.forEach(node -> {
            try {
                String[] parts = StringUtils.split(node, ":");
                Assert.notNull(parts, "Must defined");
                Assert.state(parts.length == 2, "Must be defined as 'host:port'");
                httpHosts.add(new HttpHost(parts[0], Integer.parseInt(parts[1]), esProperties.getSchema()));
            } catch (Exception e) {
                throw new IllegalStateException("Invalid ES nodes " + "property '" + node + "'", e);
            }
        });
        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[0]));

        return getRestHighLevelClient(builder, esProperties);
    }

    @Bean
    public TransportClient transportClient() {

        Settings settings = Settings.builder()
                // 不允许自动刷新地址列表
                .put("client.transport.sniff", false)
                .put("client.transport.ignore_cluster_name", true)
                .build();

        // 初始化地址
        TransportAddress[] transportAddresses = esProperties.getClusterTcpNodes().stream().map(node -> {
            String[] addressItems = node.split(":");
            try {
                TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(addressItems[0]),
                        Integer.valueOf(addressItems[1]));
                return transportAddress;
            } catch (UnknownHostException e) {
                throw CommonError.ES_ERROR.exception(e);
            }

        }).toArray(TransportAddress[]::new);

        PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings);

        TransportClient client = preBuiltTransportClient
                .addTransportAddresses(transportAddresses);
        return client;
    }


    private static RestHighLevelClient getRestHighLevelClient(RestClientBuilder builder, EsProperties esProperties) {

        // Callback used the default {@link RequestConfig} being set to the {@link CloseableHttpClient}
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(esProperties.getConnectTimeout());
            requestConfigBuilder.setSocketTimeout(esProperties.getSocketTimeout());
            requestConfigBuilder.setConnectionRequestTimeout(esProperties.getConnectionRequestTimeout());
            return requestConfigBuilder;
        });

        // Callback used to customize the {@link CloseableHttpClient} instance used by a {@link RestClient} instance.
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(esProperties.getMaxConnectTotal());
            httpClientBuilder.setMaxConnPerRoute(esProperties.getMaxConnectPerRoute());
            return httpClientBuilder;
        });

        // Callback used the basic credential auth
        EsProperties.Account account = esProperties.getAccount();
        if (!StringUtils.isEmpty(account.getUsername()) && !StringUtils.isEmpty(account.getUsername())) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(account.getUsername(), account.getPassword()));
        }
        return new RestHighLevelClient(builder);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
