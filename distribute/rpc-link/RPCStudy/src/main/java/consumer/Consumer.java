package consumer;

import framework.Invocation;
import protocol.http.HttpClient;
import provider.api.HelloService;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class Consumer {

    public static void main(String[] args) {

        HttpClient httpClient = new HttpClient();

        Invocation invocation = new Invocation(HelloService.class.getName(), "hello", new Class[]{String.class}, new Object[]{"liujun"});
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);

    }
}
