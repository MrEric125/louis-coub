package com.louis.kafka;

import org.apache.kafka.common.errors.SerializationException;

/**
 * @author John·Louis
 * @date created on 2020/2/17
 * description:
 */
public class LongSerializerTest {

    public static void main(String[] args) {
        byte[] serialize = serialize("test", 100L);
        Long deserialize = deserialize("test", serialize);
        System.out.println(deserialize);

    }
    public static byte[] serialize(String topic, Long data) {
        if (data == null)
            return null;

        return new byte[] {
                (byte) (data >>> 56),
                (byte) (data >>> 48),
                (byte) (data >>> 40),
                (byte) (data >>> 32),
                (byte) (data >>> 24),
                (byte) (data >>> 16),
                (byte) (data >>> 8),
                data.byteValue()
        };
    }

    public static Long deserialize(String topic, byte[] data) {
        if (data == null)
            return null;
        if (data.length != 8) {
            throw new SerializationException("Size of data received by LongDeserializer is not 8");
        }

        long value = 0;
        for (byte b : data) {
            value <<= 8;
            value |= b & 0xFF;
        }
        return value;
    }
}
