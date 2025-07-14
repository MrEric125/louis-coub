package com.louis.longagocode.pattern.abstractfactory.example2;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 *
 *  抽象工厂的作用主要是为子类创建某一对象提供统一的模板
 */
public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();


}
