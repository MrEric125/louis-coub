package org.example;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.StandardMetricsCollector;

public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumer, delivery) -> {
            String msg = new String(delivery.getBody(), "UTF-8");

            try {
                doWork(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("done");
            }

            System.out.println("receive Message:" + msg);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

    }

    private static void doWork(String msg) throws InterruptedException {
        for (char c : msg.toCharArray()) {
            if (c == '.') Thread.sleep(1000);
        }
    }

    public static void connect() {
        ConnectionFactory factory = new ConnectionFactory();
        // 仪表盘
        StandardMetricsCollector metrics = new StandardMetricsCollector();
        factory.setMetricsCollector(metrics);
    }
}
