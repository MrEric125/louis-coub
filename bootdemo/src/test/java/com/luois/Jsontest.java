package com.luois;

import com.MssUtil;
import com.luois.test.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author louis
 * <p>
 * Date: 2019/10/12
 * Description:
 */

@NoArgsConstructor
@Data
@Slf4j
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
    public void test4() throws IOException {
        String code = "c2l0LUVMT0ctV01PLVNKUzpzaXQtRUxPRy1XTU8tU0pT";
        byte[] b = Base64.getDecoder().decode(code);
        String str = new String(b, StandardCharsets.UTF_8);
        System.out.println(str);
    }
    @Test
    public void test5() {
        String code = "eyJyZXN1bHQiOiIwMCJ9";
        String decrypt = MssUtil.decrypt(code);
        System.out.println(decrypt);

    }
    @Test
    public void test6() {
        String code = "eyJyZXN1bHQiOiIwMCJ9";
        byte[] decode = Base64.getDecoder().decode(code);
        String response = new String(decode, StandardCharsets.UTF_8);
        System.out.println(response);
    }
    /**
     * 将包含container的object前缀截取
     * @param containerName
     * @return
     */
    public static String splitContainer(String path,String containerName) {
        String objName = path;
        if (path.startsWith("/")) {
            objName = objName.substring(1);
            return splitContainer(objName, containerName);
        }
        objName = StringUtils.replace(objName, containerName + "/", "", 1);
        return objName;

    }

    public static  String uuID(){
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    public static void main(String[] args) {
//        String contain = "//////ELOG_FLUX_WMO/zip/real/2019-09-09/deviceid/nfsc/1/2019-09-05_uploadTest1.txt";
//        String s = splitContainer(contain,"nfsc");
//        System.out.println(s);
//        String replace = StringUtils.replace(contain, "/nfsc/", "",1);
//        System.out.println(replace);

        boolean uploadFlag = false;
        int uploadNum = 0;
        while (!uploadFlag) {
            uploadNum++;
            uploadFlag = false;
            if (uploadNum == 4 && !uploadFlag) {
                System.out.println("上传没有完成");
                break;
            }
        }
        System.out.println("上传成功");
    }

    @Test
    public void test7() {
        String string = "http://sit-elog-wmo-sjs-shenzhen-xili1-oss.sit.sf-express.com:8080/v1/AUTH_sit-ELOG-WMO-SJS/louis/2019-11-07_ 134855.txt";
        String substring = string.substring(109);
        System.out.println(substring);
    }
    @Test
    public void test8() {

        char english = '#';
        char chinese = '＃';
        System.out.println((int) chinese);
        System.out.println((int) english);

    }
}
