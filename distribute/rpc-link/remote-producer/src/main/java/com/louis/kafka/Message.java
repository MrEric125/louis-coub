package com.louis.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author John·Louis
 * @date create in 2019/9/21
 * description:
 * 消息包装类，对外提供message,在封装的框架中通过Message和ProducerRecord相互转换
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<Key extends Serializable, V extends Serializable> {

    private Key key;

    private V value;

    private String topic;

    private Date sendTime;


}
