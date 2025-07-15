package com.louis.longagocode.louis;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class InterestCalculate {


    private static final Double interestMonthRate = 5.68 / (100 * 12);

    private static final Integer allPeriod = 30 * 12;


    public static void main(String[] args) {
        System.out.println(totalInterestBetween(15, 39, 360, 940000D, interestMonthRate));
        System.out.println(totalInterestBetween(1, 24, 346, 723000D, interestMonthRate));
        System.out.println(totalInterestBetween(1, 24, 24, 100000D, 0.0666 / 12));
    }

    public static Double totalInterestMonth(Integer allPeriod, Double monthlyPayment, Double allMoney) {
        return allPeriod * monthlyPayment - allMoney;
    }

    /**
     * @param startPeriod 包含
     * @param endPeriod   包含
     * @param allPeriod
     * @param allMoney
     * @param mouthlyRate
     * @return
     */
    public static Double totalInterestBetween(Integer startPeriod, Integer endPeriod, Integer allPeriod, Double allMoney, Double mouthlyRate) {
        Double allInterestBetween = 0D;
        for (int i = startPeriod; i <= endPeriod; i++) {
            allInterestBetween = allInterestBetween + mouthlyInterest(i, allPeriod, allMoney, mouthlyRate);
        }
        return allInterestBetween;

    }


    /**
     * 每月支付的金額
     *
     * @param allPeriod
     * @param allMoney
     * @return
     */
    public static Double monthlyPayment(Integer allPeriod, Double allMoney, Double mouthlyRate) {

        Double interest = InterestCalculate.interest(allPeriod, mouthlyRate);
        return allMoney * (interestMonthRate * interest) / (interest - 1);
    }

    /**
     * 每月應還利息
     *
     * @param currentPeriod
     * @param allPeriod
     * @param allMoney
     * @return
     */
    public static Double mouthlyInterest(Integer currentPeriod, Integer allPeriod, Double allMoney, Double mouthRate) {
        return allMoney * interestMonthRate * (interest(allPeriod, mouthRate) - interest(currentPeriod - 1, mouthRate)) / (interest(allPeriod, mouthRate) - 1);

    }

    public static Double interest(Integer period, Double mouthRate) {

        if (mouthRate == 0D) {
            throw new IllegalArgumentException("利率不能为空");
        }

        Double rate = 1 + mouthRate;
        Double returnRate = rate;
        if (period == 0) {
            returnRate = 1D;
        }
        for (int i = 1; i < period; i++) {
            returnRate = returnRate * rate;
        }
        return returnRate;
    }


}
