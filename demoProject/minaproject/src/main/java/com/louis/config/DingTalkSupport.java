package com.louis.config;

import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author louis
 * @date 2022/6/28
 */
@Slf4j
@Service
public class DingTalkSupport {

    @Autowired
    private Environment environment;

    @Autowired
    DingTalkClient dingTalkClient;

    public static void send(String msg) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        request.setTimestamp(System.currentTimeMillis());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();

        text.setContent(msg);
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
        request.setAt(at);
        try {
            DingTalkClient dingTalkClient = DingTalkConfig.dingTalkClient;
            if (Objects.nonNull(dingTalkClient)) {
                OapiRobotSendResponse response = dingTalkClient.execute(request);
                log.info(response.getBody());
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }

    public  void sendDingTalk(String msg)  {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        request.setTimestamp(System.currentTimeMillis());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();

        text.setContent(msg);
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
        request.setAt(at);
        try {

            OapiRobotSendResponse response = dingTalkClient.execute(request);
            log.info(response.getBody());
        } catch (Exception e) {
            log.error("error", e);

        }
    }
}
