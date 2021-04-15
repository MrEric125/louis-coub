package com.java8.collectors;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author jun.liu
 * @date created on 2020/10/27
 * description:
 */
public class GroupingToOptional {

    @Test
    public void test2() {
        ReportForm reportForm11 = new ReportForm("8",null,11);
        ReportForm reportForm21 = new ReportForm("8",13,null);
        ReportForm reportForm12 = new ReportForm("9",null,12);
        ReportForm reportForm22 = new ReportForm("9", 14, null);
        ReportForm reportForm31 = new ReportForm("9", 14, null);
        ReportForm reportForm32 = new ReportForm("9", 14, null);


        ArrayList<ReportForm> list = Lists.newArrayList(reportForm11, reportForm12, reportForm21, reportForm22, reportForm31, reportForm32);

        for (int i = 0; i < 10000; i++) {
            list.add(new ReportForm("9", 14, 2));
            list.add(new ReportForm("10", 14, 3));
        }

        Integer collect = list.stream().mapToInt(item -> {
            if (Objects.isNull(item.getHotLine())) {
                return 0;
            } else {
                return item.getHotLine();
            }
        }).sum();
//        System.out.println(collect);

        Map<String, Integer> collect1 = list.stream().collect(Collectors.groupingBy(ReportForm::getTime, Collectors.reducing(0, item -> {
            if (Objects.isNull(item.getHotLine())) {
                return 0;
            } else {
                return item.getHotLine();
            }
        }, Integer::sum)));

        groupToObjMethod1(list);

        groupToObjMethod2(list);

    }


    public void groupToObjMethod1(List<ReportForm> list) {
        long start1 = System.currentTimeMillis();
        BinaryOperator<ReportForm> accumulator = (t1, t2) -> {
            //todo 这个地方会不会有性能问题呢？
            //每次规约操作的时候都会新建一个对象
            ReportForm reportForm = new ReportForm();

            int i1 = t1.getHotLine() == null ? 0 : t1.getHotLine();
            int i2 = t2.getHotLine() == null ? 0 : t2.getHotLine();
            reportForm.setHotLine(i1 + i2);
            int i3 = t1.getOnLine() == null ? 0 : t1.getOnLine();
            int i4 = t2.getOnLine() == null ? 0 : t2.getOnLine();
            reportForm.setOnLine(i3 + i4);
            reportForm.setTime(t1.getTime());
            System.out.println("tt  " + t1);
            return reportForm;
        };

        Map<String, Optional<ReportForm>> collect2 = list.stream().collect(Collectors.groupingBy(ReportForm::getTime, Collectors.reducing(accumulator)));
        long end1 = System.currentTimeMillis();
        System.out.println("==========第一次============" + (end1 - start1));
    }

    public void groupToObjMethod2(List<ReportForm> list) {
        long start2 = System.currentTimeMillis();
        Map<String, List<ReportForm>> collect3 = list.stream().collect(Collectors.groupingBy(ReportForm::getTime));

        List<ReportForm> formList = Lists.newArrayList();


        for (String s : collect3.keySet()) {
            List<ReportForm> reportForms = collect3.get(s);
            int getHotLine = reportForms.stream().mapToInt(item->{
                if (Objects.isNull(item.getHotLine())) {
                    return 0;
                } else {
                    return item.getHotLine();
                }
            }).sum();
            int getOnLine = reportForms.stream().mapToInt(item->{
                if (Objects.isNull(item.getOnLine())) {
                    return 0;
                } else {
                    return item.getOnLine();
                }
            }).sum();
            ReportForm reportForm = new ReportForm();
            reportForm.setHotLine(getHotLine);
            reportForm.setOnLine(getOnLine);
            reportForm.setTime(s);
            formList.add(reportForm);
        }
        long end2 = System.currentTimeMillis();

        System.out.println("==========第二次============"+(end2 - start2));

//        System.out.println(JSON.toJSONString(formList, true));
    }




}
