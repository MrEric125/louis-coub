package com.luois;

import java.util.Random;

public class Demo2 {


    public static void main(String[] args) {

        double initAmount = 7000;
        double startAmount = initAmount;

        double win = 0;

        double rate = 0.5;
        double increaseRate = 0.01;
        double decreaseRate = 0.01;

        for (int i = 0; i < 10000; i++) {
            double v= netAmount(initAmount, rate, increaseRate, decreaseRate);
            win = win + v - initAmount;
            if (startAmount + win <= 0) {
                System.out.println("操作" + i + "次，被踢出局");
                return;
            }

            if (i % 100 == 0) {
                initAmount = startAmount + win;
            }
            System.out.println("initAmount:" + initAmount + ";v = " + v + ";win=" + win);

        }
    }

    public static double netAmount(double amount, double rate, double increaseRate, double decreaseRate) {

        Random random = new Random();
        double v = random.nextDouble();
        if (v < rate) {
            return amount + amount * increaseRate;
        }
        return amount - amount * decreaseRate;
    }

}
