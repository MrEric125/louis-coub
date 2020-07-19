package com.pattern.abstractfactory;

import com.pattern.abstractfactory.example2.Army;
import com.pattern.abstractfactory.example2.Castle;
import com.pattern.abstractfactory.example2.King;
import com.pattern.abstractfactory.example2.KingdomFactory;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class EricKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return null;
    }

    @Override
    public King createKing() {
        return null;
    }

    @Override
    public Army createArmy() {
        return null;
    }
}
