package com.luois;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.*;

/**
 * @author louis
 * <p>
 * Date: 2019/10/12
 * Description:
 */

public class Jsontest {

    @Test
    public void test() {

//        String json= "{\"allAlarm\":\"10\",\"waveAlarm\":\"20\",\"pickingAlarm\":\"30\",\"checkedAlarm\":\"40\",\"shippedAlarm\":\"50\"}";
        String json = "";
        Gson gson = new Gson();
        Map<String,String > fromJson = gson.fromJson(json, Map.class);
        if (fromJson != null && fromJson.size() != 0) {
            fromJson.forEach((key, value) -> {
                System.out.println("key is : " + key);
                Integer integer = Integer.valueOf(value);
                System.out.println("value is : "+integer);
            });
        }
    }
    @Test
    public void test2() {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int j = random.nextInt(2);
            List<Integer> resultList = null;
            if (j <= 0) {
                resultList = new ArrayList<>();
                resultList.add(423);
                resultList.add(332);
            }
            String s = resultList
                    .stream().findFirst().map(String::valueOf).orElse("zhang");
            System.out.println(s);
        }


    }

    @Test
    public void test3() {



    }
}
