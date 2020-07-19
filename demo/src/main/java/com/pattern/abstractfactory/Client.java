package com.pattern.abstractfactory;

import com.pattern.abstractfactory.example2.*;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */

public class Client {


    public static void main(String[] args) {
        example2();

    }

    public static void example1() {
        Set<String> set = new ConcurrentSkipListSet<>();
        List<String> voctor = new Vector<>();
        Set<String> treeSet = new TreeSet<>();

    }

    public static void example2() {



        System.out.println("louis");
        KingdomFactory factory = FactoryCreator.createFactory(FactoryCreator.KingdomType.LOUIS);

        System.out.println(factory.createArmy().getDescription());
        System.out.println(factory.createCastle().getDescription());
        System.out.println(factory.createKing().getDescription());


        System.out.println("eric");
        KingdomFactory louisFactory = FactoryCreator.createFactory(FactoryCreator.KingdomType.ERIC);
        System.out.println(louisFactory.createArmy().getDescription());
        System.out.println(louisFactory.createCastle().getDescription());
        System.out.println(louisFactory.createKing().getDescription());

    }


    public static class FactoryCreator{
        public enum KingdomType{
            LOUIS, ERIC,
        }

        public static KingdomFactory createFactory(KingdomType type) {
            switch (type) {
                case ERIC:
                    return new EricKingdomFactory();
                case LOUIS:
                    return new LouisKingdomFactory();
                default:
                    throw new IllegalArgumentException("kingdomType not supported.");

            }
        }
    }
}
