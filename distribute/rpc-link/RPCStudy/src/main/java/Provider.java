import framework.RemoteURL;
import protocol.http.HttpServer;
import provider.LocalRegister;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.RemoteMapRegister;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class Provider {

    public static void main(String[] args) {
//        本地注册

        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

//        远程注册
        RemoteURL url = new RemoteURL("localhost", 8080);
        RemoteMapRegister.regist(HelloService.class.getName(), url);


//        启动tomcat

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
