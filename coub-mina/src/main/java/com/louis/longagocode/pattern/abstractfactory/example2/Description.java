package com.louis.longagocode.pattern.abstractfactory.example2;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public interface Description {

     default String getDescription(){
         return this.getClass().getSimpleName();
     }
}
