package com.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Louis
 * @date created on 2020/11/30
 * description:
 */
public enum Fivarite {

    BALL(1, "BALL");

        @EnumValue
        private int value;
        private String name;

    Fivarite(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
}
