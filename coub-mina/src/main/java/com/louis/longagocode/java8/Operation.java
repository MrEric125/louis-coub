package com.louis.longagocode.java8;

import java.util.function.DoubleBinaryOperator;

/**
 * @author Louis
 * @date created on 2020/12/12
 * description:
 */
public enum Operation {

    PLUS("+", Double::sum),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/",(x,y)-> x/y);

    private final String symbol;

    private final DoubleBinaryOperator operator;

    Operation(String symbol, DoubleBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;

    }

    public  double apply(double x, double y){
        return operator.applyAsDouble(x, y);

    }


}
