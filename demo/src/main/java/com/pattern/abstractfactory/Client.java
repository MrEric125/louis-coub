package com.pattern.abstractfactory;

import com.pattern.abstractfactory.example2.*;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
@Data
public class Client {

    private King king;
    private Castle castle;
    private Army army;

    public static void main(String[] args) {
        example2();

    }

    public static void example1() {
        Set<String> set = new ConcurrentSkipListSet<>();
        List<String> voctor = new Vector<>();
        Set<String> treeSet = new TreeSet<>();

    }

    public static void example2() {

        Client client = new Client();

        System.out.println("louis");
        client.createKingdom(FactoryCreator.createFactory(FactoryCreator.KingdomType.LOUIS));
        System.out.println(client.getArmy().getDescription());
        System.out.println(client.getCastle().getDescription());
        System.out.println(client.getKing().getDescription());


        System.out.println("eric");
        client.createKingdom(FactoryCreator.createFactory(FactoryCreator.KingdomType.ERIC));
        System.out.println(client.getArmy().getDescription());
        System.out.println(client.getCastle().getDescription());
        System.out.println(client.getKing().getDescription());

    }

    public void createKingdom(KingdomFactory factory) {
        setArmy(factory.createArmy());
        setCastle(factory.createCastle());
        setKing(factory.createKing());

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
