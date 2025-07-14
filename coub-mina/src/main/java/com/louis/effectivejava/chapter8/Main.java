package com.louis.effectivejava.chapter8;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // Attack the internals of a Period instance
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78);  // Modifies internals of p!


        System.out.println("Period start: " + p.end());
    }
}
