package com.louis.longagocode.effectivejava.chapter6;

public enum Operation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    ;

    public abstract double apply(double x, double y);

    private final String symbol;
    Operation(String symbol){
        this.symbol = symbol;
    }
}
