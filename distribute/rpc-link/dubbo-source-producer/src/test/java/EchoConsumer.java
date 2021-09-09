import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class EchoConsumer {

    public static void main(String[] args) {
        ReferenceConfig<EchoService> referenceConfig = new ReferenceConfig<>();
        //这些都可以注册成单例bean
        referenceConfig.setApplication(new ApplicationConfig("java-echo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(EchoService.class);
        EchoService echoService = referenceConfig.get();
        String hello_world = echoService.echo("Hello world");
        System.out.println(hello_world);


    }
}
