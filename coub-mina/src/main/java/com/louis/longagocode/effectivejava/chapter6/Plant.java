package com.louis.longagocode.effectivejava.chapter6;


/**
 * 不建议使用int类型与String 类型表达枚举类型
 */
public enum Plant {

    MERCURY(3.3023 + 23, 2.439e6),
    ;

    private final double mass;
    private final double radius;
    private final double surfacegravity;
    private static final double g = 6.6730e-11;

    Plant(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfacegravity = g * mass / (radius * mass);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfacegravity() {
        return surfacegravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfacegravity;
    }

}

