package com.louis.mybatis.enums;

import org.apache.commons.lang3.StringUtils;

public interface IBaseEnum {
    default int getValue() {
        throw new UnsupportedOperationException("未实现getValue方法");
    }

    default String getCode() {
        return StringUtils.EMPTY;
    }

    default String getResourceCode() {
        return StringUtils.EMPTY;
    }

    default String getName() {
        return StringUtils.EMPTY;
    }

    default int getOrder() {
        return 0;
    }

    default int getType() {
        return -1;
    }

    static <E extends Enum<E> & IBaseEnum> E getByValue(Class<E> enumClass, Integer value) {
        if (value == null) return null;
        E[] enumConstants = enumClass.getEnumConstants();
        for (E item : enumConstants) {
            if (value.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }

    static <E extends Enum<E> & IBaseEnum> E getByCode(Class<E> enumClass, String code) {
        if (StringUtils.isBlank(code)) return null;
        E[] enumConstants = enumClass.getEnumConstants();
        for (E item : enumConstants) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }
}
