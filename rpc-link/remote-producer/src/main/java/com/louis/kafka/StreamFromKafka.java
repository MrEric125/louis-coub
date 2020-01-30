package com.louis.kafka;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

import java.util.Properties;

/**
 * @author John·Louis
 * @date created on 2020/1/30
 * description:
 * 毫秒级延时，实时流处理，并非微批处理
 * 窗口允许乱序数据，
 *  优势：
 * 实时流处理
 *  KafkaStream是基于Kafka的流式处理，集成框架少，集成成本低
 *  Kafka本事提供数据持久化
 * 可以在线动态调整
 * 数据清洗案例：对我们找到的数据进行二次处理
 *
 */
public class StreamFromKafka {

    public static void main(String[] args) {

//         1.设置配置信息
//        2. 创建输出流
        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"filter");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        StreamsConfig config = new StreamsConfig(properties);

        Topology topology = new Topology();
//        输入源的主题
        topology.addSource("source", "first")
//                处理数据的阶段名称， source表示的是输入源是谁
                .addProcessor("processor", new ProcessorSupplier<byte[], byte[]>() {
                    @Override
                    public Processor<byte[], byte[]> get() {

                        return new LogProcessor();
                    }
                }, "source")
//                输出到下一级是哪个topic
                .addSink("sink", "second", "processor");


        new KafkaStreams(topology, properties);
//

    }


}
