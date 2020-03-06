package com.louis.kafka;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
@Data
public class Message<T extends Serializable> {


    private String id;

    private String topic;

    private T data;





}
