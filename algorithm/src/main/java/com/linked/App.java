package com.linked;

public class App {
    public static void main(String[] args) {
        MyList<String> list = new SingleLink<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        System.out.println(list.find(2));
        list.addIndex(2, "zhaoliu");
        System.out.println(list.find(2));

    }
}
