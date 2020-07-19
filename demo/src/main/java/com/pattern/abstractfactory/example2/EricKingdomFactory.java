package com.pattern.abstractfactory.example2;


/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class EricKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new EricCastle();
    }

    @Override
    public King createKing() {
        return new EricKing();
    }

    @Override
    public Army createArmy() {
        return new EricArmy();
    }
}
