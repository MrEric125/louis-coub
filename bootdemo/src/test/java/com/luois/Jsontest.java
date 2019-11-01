package com.luois;

import com.google.gson.Gson;
import com.luois.test.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author louis
 * <p>
 * Date: 2019/10/12
 * Description:
 */

@NoArgsConstructor
@Data
public class Jsontest {

    /**
     * name : zhangsan
     * age : 20
     * job : 20
     */

    private String name;
    private String age;
    private String job;

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
    public void test3() throws IOException {
        String json = "{\"name\":\"zhangsan\",\"age\":\"20\",\"job\":\"20\"}";
//        String json = "{\"name\":\"zhangsan\",\"age\":\"20\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Person test = objectMapper.readValue(json, new TypeReference<Person>(){});
        System.out.println(test);
    }
    @Test
    public void test4() throws IOException {
        String code = "c2l0LUVMT0ctV01PLVNKUzpzaXQtRUxPRy1XTU8tU0pT";
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(code);
        String str = new String(b, StandardCharsets.UTF_8);
        System.out.println(str);
    }
}
