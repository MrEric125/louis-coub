package com.louis.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2020/3/4
 * Description:
 */
@Data
public class KeyValue implements Serializable {

    private String key;

    private String value;

}
