package com.louis.mybatis.provider;


public class ClassIntrospectorTest {




    public static void main(String[] args) throws IllegalAccessException {
        CalculateObjSize ci = new CalculateObjSize();

        ObjectInfo res;

        Obj obj = new Obj();
        obj.setString1("121212121222222222222222222222222222222222222222222222222222222222222222222");

        res = ci.introspect( obj );
        System.out.println( res.getDeepSize() );
    }

}
