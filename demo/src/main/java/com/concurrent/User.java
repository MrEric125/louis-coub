package com.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author louis
 * <p>
 * Date: 2019/8/20
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private int age;
}
