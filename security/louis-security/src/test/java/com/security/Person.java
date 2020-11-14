package com.security;

import lombok.Data;

/**
 * @author louis
 * <p>
 * Date: 2019/12/18
 * Description:
 */

public class Person {
        private String name;
        private String age;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAge() {
                return age;
        }

        public void setAge(String age) {
                this.age = age;
        }
}
