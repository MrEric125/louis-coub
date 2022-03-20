package com.luois;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John·Louis
 * @date created on 2020/2/12
 * description:
 */
public class Test {

    public static void main(String[] args) {
        String var="-12399999";

        Map<String,Integer> strMapInt=new HashMap<>();
        strMapInt.put("0",0);
        strMapInt.put("1",1);
        strMapInt.put("2",2);
        strMapInt.put("3",3);
        strMapInt.put("4",4);
        strMapInt.put("5",5);
        strMapInt.put("6",6);
        strMapInt.put("7",7);
        strMapInt.put("8",8);
        strMapInt.put("9",9);
        String[] strArr=var.split("");

        int length=strArr.length;
        Integer result=0;
        boolean isDeg = false;

        for(int i=0;i<length;i++){

            if (i == 0 && strArr[0].equals("-")) {
                isDeg = true;
                continue;
            }

            Integer itemInt=strMapInt.get(strArr[i]);

            if (Objects.isNull(itemInt)) {
                throw new NumberFormatException("int format error:" + var);
            }

            result = (result + itemInt);
            if (i != length - 1) {
                result = result * 10;
            }
        }
        if (isDeg) {
            if (result < Integer.MIN_VALUE) {
                throw new RuntimeException("result smaller than max integer");
            }
            result = -result;
        } else {
            if (result > Integer.MAX_VALUE) {
                throw new RuntimeException("result bigger than max integer");
            }
        }
        System.out.print(result);


    }



}
