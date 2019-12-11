package com.java8;

import java.util.Optional;

/**
 * @author John·Louis
 * @date create in 2019/11/29
 * description:
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("aaa");
        Optional<String> s = optional.map(a -> a+"ss");
        s.get();

    }
}
