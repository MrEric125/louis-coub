package com.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Louis
 * @date created on 2020/11/30
 * description:
 */
public enum Fivarite {

        DMALL(1, "多点"),
        BRAND(3, "品牌"),
        CARRIER(4, "承运商"),
        PARTNER(5, "来客"),
        POP(6, "POP");
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
