package com.java8;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author louis
 * <p>
 * Date: 2019/9/24
 * Description:
 */
public interface BuilderFactory {

    String create(Integer name);

    Map<Integer, String> map = Maps.newHashMap();
    static BuilderFactory factory(Consumer<Builder> consumer) {
        consumer.accept(map::put);
        return map::get;
    }

    default void delete(Integer name){

    }

}
