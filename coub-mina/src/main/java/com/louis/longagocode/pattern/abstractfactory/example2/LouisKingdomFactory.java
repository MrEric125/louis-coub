package com.louis.longagocode.pattern.abstractfactory.example2;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class LouisKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new LouisCastle();
    }

    @Override
    public King createKing() {
        return new LouisKing();
    }

    @Override
    public Army createArmy() {
        return new LouisArmy();
    }
}
