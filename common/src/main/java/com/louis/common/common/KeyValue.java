package com.louis.common.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author louis
 * @Date 2020/1/16
 * description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyValue<K, V> implements Serializable {
    private static final long serialVersionUID = 4893280118017319009L;

    private K k;

    private V v;

}
