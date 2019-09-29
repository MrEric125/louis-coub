package com.louis.kafka;

import lombok.Data;

import java.util.Date;

/**
 * @author John·Louis
 * @date create in 2019/9/21
 * description:
 */
@Data
public class Message {

    private Long id;

    private String msg;

    private String topic;

    private Date sendTime;


}
