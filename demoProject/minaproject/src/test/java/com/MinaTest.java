package com;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Slf4j
@Setter
@Getter
public class MinaTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {

            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        launch(args);
        Long timestamp = System.currentTimeMillis();
        String url = "";
        url = url + "&timestamp=" + timestamp;
//        url = url + "&sign=" + sign;
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        request.setTimestamp(System.currentTimeMillis());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("启动成功");

        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        log.info(response.getBody());

    }


    static String char_count(String str) {
        if (str==null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char c = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char s = str.charAt(i);
            if (s == c) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count);
                    sb.append(c);
                    count = 1;
                } else {
                    sb.append(count);
                    sb.append(c);
                }
            }
            c = s;
        }
        sb.append(count);
        sb.append(c);

        return sb.toString();

    }

}
