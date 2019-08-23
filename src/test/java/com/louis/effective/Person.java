package com.louis.effective;

import lombok.*;

/**
 * @author louis
 * <p>
 * Date: 2019/7/26
 * Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Person {

    private long id;
    private String name;

    private int age;


    private int score;

    private String record;

    private String description;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", record='" + record + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
