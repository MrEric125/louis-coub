//package com.louis.kafka;
//
//import com.louis.common.common.KeyValue;
//import com.louis.common.common.HttpResult;
//import com.louis.common.common.HttpResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
///**
// * @author John·Louis
// * @date create in 2019/10/3
// * description:
// */
//@RestController
//@RequestMapping("/kafka")
//public class KafkaConsumerController {
//
//
////    @Autowired
//    MessageConsumer messageConsumer;
//
//    @RequestMapping("consumer")
//    public HttpResult consumer() {
//        messageConsumer.defaultMessageConsumer();
//        KeyValue<Long,String> consumerMessage= Optional.ofNullable(messageConsumer.getQueueMessage()).orElseGet(() -> {
//            String message = messageConsumer.getStringQueueMessage();
//            return new KeyValue<>(1L, message);
//        });
//        return HttpResult.wrap(consumerMessage);
//    }
//
//}
