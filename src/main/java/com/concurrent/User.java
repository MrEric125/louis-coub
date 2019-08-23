package com.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author louis
 * <p>
 * Date: 2019/8/20
 * Description:
 */
@Data
@AllArgsConstructor
public class User {
    private String username;
    private int age;
}
