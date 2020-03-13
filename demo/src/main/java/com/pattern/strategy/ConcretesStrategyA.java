package com.pattern.strategy;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class ConcretesStrategyA implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法实现");
    }
}
