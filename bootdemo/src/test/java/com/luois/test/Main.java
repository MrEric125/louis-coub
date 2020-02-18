package com.luois.test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author John·Louis
 * @date created on 2020/2/11
 * description:
 */
public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String content = sc.nextLine();
        String world = sc.nextLine();
        String[] s = content.split(""); //正则表达式实用性更强( str.split("\\s+"))
        int count = Arrays.stream(s).filter(world::equals).mapToInt(e -> 1).sum();
        System.out.println(count);


    }
}
