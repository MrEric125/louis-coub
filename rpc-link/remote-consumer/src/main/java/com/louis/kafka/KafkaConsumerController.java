package com.louis.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date create in 2019/10/3
 * description:
 */
@RestController
@RequestMapping("/kafka")
public class KafkaConsumerController {


    @Autowired
    MessageConsumer messageConsumer;
    @RequestMapping("consumer")
    public String consumer() {
        return messageConsumer.consumerMessage;
    }
}
