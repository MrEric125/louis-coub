package com.louis.config;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @author louis
 * @date 2022/6/27
 */
@Slf4j
@Configuration
@EnableAutoConfiguration
@ConditionalOnProperty(value = "ding.talk.callback")
public class DingTalkConfig {


    @Value("${ding.talk.callback:http://localhost:8080/callback}")
    private String callBack;

    @Value("${ding.talk.token:token}")
    private String token;

    @Value("${ding.talk.originalSign:originalSign}")
    private String originalSign;

    public static DingTalkClient dingTalkClient;

    @Bean
    public DingTalkClient dingTalkClient() {

        String url = buildUrl();
        DingTalkClient client = new DefaultDingTalkClient(url);
        DingTalkConfig.dingTalkClient = client;
        return client;
    }
    public  String buildUrl() {
        String url = callBack + "?access_token=" + token;
        long timestamp = System.currentTimeMillis();
        String sign = sign(originalSign, timestamp);
        url = url + "&timestamp=" + timestamp;
        url = url + "&sign=" + sign;
        return url;
    }

    public static String sign(String secret,Long timestamp)  {
        String stringToSign = timestamp + "\n" + secret;
        String sign = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        } catch (Exception e) {
            log.error("加签失败", e);
        }
        return sign;

    }

}

