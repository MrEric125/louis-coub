package com.ajax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John·Louis
 * @date create in 2019/9/8
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorsRequest {

    private long id;
    private String name;
    private int age;


}
