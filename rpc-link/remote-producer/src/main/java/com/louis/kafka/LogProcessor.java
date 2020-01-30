package com.louis.kafka;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @author John·Louis
 * @date created on 2020/1/30
 * description:
 */
public class LogProcessor implements Processor<byte[], byte[]> {
    private static ProcessorContext context;


    /**
     * 初始化相关
     *
     * @param processorContext
     */
    @Override

    public void init(ProcessorContext processorContext) {
        context = processorContext;

    }

    /**
     * 业务处理相关
     *
     * @param key
     * @param value
     */
    @Override
    public void process(byte[] key, byte[] value) {
        String input = new String(value);
        if (input.contains(">>>")) {
            String[] split = input.split(">>>");
            String trim = split[1].trim();
            context.forward(key, trim.getBytes());
        } else {
            context.forward(key, value);

        }

    }

    /**
     * 流关闭相关
     */
    @Override
    public void close() {

    }
}
