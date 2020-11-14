package com.exam;


import java.util.Scanner;

/**
 * @author John·Louis
 * @date created on 2020/2/14
 * description:
 */
public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split(" "); //正则表达式实用性更强( str.split("\\s+"))
        int length = s[s.length - 1].length();
        System.out.println(length);
    }
}
