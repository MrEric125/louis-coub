package com.louis.minashop.searchable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author John·Louis
 * @date 2019年5月30日22:53:36
 */
public final class Condition implements SearchFilter {

    //查询参数分隔符
    public static final String separator = "_";

    private String key;
    private String searchProperty;
    private SearchOperator operator;
    private Object value;

    /**
     * 根据查询key和值生成Condition
     *
     * @param key   如 name_like
     * @param value
     * @return
     */
    static Condition newCondition(final String key, final Object value) throws SearchException {

        Assert.notNull(key, "Condition key must not null");

        String[] searchs = StringUtils.split(key, separator);

        if (searchs.length == 0) {
            throw new SearchException("Condition key format must be : property or property_op");
        }

        String searchProperty = searchs[0];

        SearchOperator operator = null;
        if (searchs.length == 1) {
            operator = SearchOperator.custom;
        } else {
            try {
                operator = SearchOperator.valueOf(searchs[1]);
            } catch (IllegalArgumentException e) {
                throw new InvalidSearchOperatorException(searchProperty, searchs[1]);
            }
        }

        boolean allowBlankValue = SearchOperator.isAllowBlankValue(operator);
        boolean isValueBlank = (value == null);
        isValueBlank = isValueBlank || (value instanceof String && StringUtils.isBlank((String) value));
        isValueBlank = isValueBlank || (value instanceof List && ((List) value).size() == 0);
        //过滤掉空值，即不参与查询
        if (!allowBlankValue && isValueBlank) {
            return null;
        }

        Condition searchFilter = newCondition(searchProperty, operator, value);

        return searchFilter;
    }


    /**
     * 根据查询属性、操作符和值生成Condition
     *
     * @param searchProperty
     * @param operator
     * @param value
     * @return
     */
    static Condition newCondition(final String searchProperty, final SearchOperator operator, final Object value) {
        return new Condition(searchProperty, operator, value);
    }

    /**
     * @param searchProperty 属性名
     * @param operator       操作
     * @param value          值
     */
    private Condition(final String searchProperty, final SearchOperator operator, final Object value) {
        this.searchProperty = searchProperty;
        this.operator = operator;
        this.value = value;
        this.key = this.searchProperty + separator + this.operator;
    }

    public String getKey() {
        return key;

    }

    public String getSearchProperty() {
        return searchProperty;
    }

    /**
     * 获取 操作符
     *
     * @return
     */
    public SearchOperator getOperator() throws InvalidSearchOperatorException {
        return operator;
    }

    /**
     * 获取自定义查询使用的操作符
     * 1、首先获取前台传的
     * 2、返回空
     *
     * @return
     */
    public String getOperatorStr() {
        if (operator != null) {
            return operator.getSymbol();
        }
        return "";
    }

    public Object getValue() {
        return value;
    }


    public void setValue(final Object value) {
        this.value = value;
    }

    public void setOperator(final SearchOperator operator) {
        this.operator = operator;
    }

    public void setSearchProperty(final String searchProperty) {
        this.searchProperty = searchProperty;
    }


    /**
     * 得到实体属性名
     *
     * @return
     */
    public String getEntityProperty() {
        return searchProperty;
    }

    /**
     * 是否是一元过滤 如is null is not null
     *
     * @return
     */
    public boolean isUnaryFilter() {
        String operatorStr = getOperator().getSymbol();
        return StringUtils.isNotEmpty(operatorStr) && operatorStr.startsWith("is");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Condition that = (Condition) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

}