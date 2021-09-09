import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubbo
@ComponentScan
public class ConsumerConfiguration {

    // consumer
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName("echo-annotation-consumer");
        return config;
    }
    @Bean
    public ConsumerConfig consumerConfig() {
        return new ConsumerConfig();
    }
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("localhost");
        registryConfig.setPort(2181);
        return registryConfig;
    }

    // provider
    @Bean
    public ProviderConfig providerConfig() {
        return new ProviderConfig();
    }

//    @Bean
//    public ApplicationConfig applicationConfig() {
//        ApplicationConfig applicationConfig=new ApplicationConfig();
//        applicationConfig.setName("echo-annotation-provider");
//        return applicationConfig;
//    }
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;

    }

}
