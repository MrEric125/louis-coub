package com.linked;

public class App {
    public static void main(String[] args) {
        SingleLink list = new SingleLink();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(9);
        SingleLink list2 = new SingleLink();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);
        list2.add(9);
        list2.add(10);
        list2.add(11);

        SingleLink singleLink = new SingleLink();
        singleLink.compare(list.getFirst(), list2.getFirst(), singleLink);

        System.out.println(singleLink);

    }


}
