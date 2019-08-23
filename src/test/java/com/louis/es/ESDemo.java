//package com.louis.es;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//
//import java.io.IOException;
//
///**
// * @author louis
// * <p>
// * Date: 2019/7/9
// * Description:
// */
//public class ESDemo {
//
//    private TransportClient client;
//
//    public static final String index = "website";
//    public static final String type = "blog";
//
//
//    @Before
//    public void before() {
//        Settings settings = Settings.EMPTY;
//      /*  try {
//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("129.28.189.234"), 9300));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }*/
//    }
//
//
//    @Test
//    public void test001() {
//
//        try {
//            GetResponse documentFields = client.prepareGet("website", "blog", "123").execute().actionGet();
//            System.out.println(documentFields.getSourceAsString());
//            List<DiscoveryNode> discoveryNodes = client.listedNodes();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("json", discoveryNodes);
//
//            String s = jsonObject.toString();
//            System.out.println(s);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test2() {
//        XContentBuilder doucument = null;
//        try {
//            doucument = XContentFactory.jsonBuilder().startObject()
//                    .field("first_name", "zhangsan")
//                    .field("last_name", "Zhangs")
//                    .field("age", 66)
//                    .field("about", "api test").endObject();
//            IndexResponse response = client.prepareIndex(index, type, "2").setSource(doucument).get();
//            System.out.println(response);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (doucument != null) {
//                doucument.close();
//            }
//
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @After
//    public void after() {
//
//        if (client != null) {
//            client.close();
//        }
//    }
//}
