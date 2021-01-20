package com.nio.buffer;

import lombok.Getter;
import lombok.Setter;

import java.nio.CharBuffer;

/**
 * @author jun.liu
 * @since 2021/1/19 10:47
 */
@Setter
@Getter
public class DemoBuffer implements Cloneable {


    public static void main(String[] argv) throws Exception {
        CharBuffer buffer = CharBuffer.allocate(50);
        while (fillBuffer(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }


        return (true);
    }

    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };




}
