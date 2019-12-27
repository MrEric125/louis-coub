package com.java8;

import lombok.*;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2019/12/23
 * Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 123451235423453L;

    private Integer weight;

    private Integer age;

    private String name;

    private String contrary;

    private String type;


}
