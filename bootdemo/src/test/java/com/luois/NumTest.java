package com.luois;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author louis
 * <p>
 * Date: 2019/10/31
 * Description:
 */
public class NumTest {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("d:\\user\\80003996\\desktop\\lognum\\novatar_20191028.35.log"));
            StringBuffer sb = new StringBuffer();
            String str = null;
            while((str = br.readLine()) != null) {
                sb.append(str);
            }

            String regex = "lotLabel";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sb);

            int num = 0;
            while(matcher.find()) {
                num++;
            }

            System.out.println("===================");
            System.out.println(regex+" 关键字出现的次数为： " + num);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != br) {
                    //关闭资源
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
