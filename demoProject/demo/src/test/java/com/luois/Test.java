package com.luois;

import com.google.common.collect.Lists;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John·Louis
 * @date created on 2020/2/12
 * description:
 */
public class Test {

    private static Consumer<String> consumer= s -> {

    };
    private static Function<String,String> transformation=s-> s+"121212";
    private static Supplier<String> supplier= ()-> "sss";

    public static void main(String[] args) {
//        StaticTest.method1();

        Consumer<Tuple2<Consumer<String>, String>> biConsumer = tuple -> {

            for(int i = 0; i < 10; i++){
                // 类型正确，开启编译器
                tuple.getT1().accept(tuple.getT2());
            }
        };



        biConsumer.accept(
                Tuples.of(
                        consumer,
                        transformation.apply(supplier.get())
                )
        );

    }


}
