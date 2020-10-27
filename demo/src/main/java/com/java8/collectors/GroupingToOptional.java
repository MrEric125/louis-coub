package com.java8.collectors;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

        ArrayList<ReportForm> list = Lists.newArrayList(reportForm11, reportForm12, reportForm21, reportForm22);

        Integer collect = list.stream().mapToInt(item -> {
            if (Objects.isNull(item.getHotLine())) {
                return 0;
            } else {
                return item.getHotLine();
            }
        }).sum();
        System.out.println(collect);

        Map<String, Integer> collect1 = list.stream().collect(Collectors.groupingBy(ReportForm::getTime, Collectors.reducing(0, item -> {
            if (Objects.isNull(item.getHotLine())) {
                return 0;
            } else {
                return item.getHotLine();
            }
        }, Integer::sum)));
        System.out.println(collect1);

        //todo 这个地方会不会有性能问题呢？
        //todo 每次规约操作的时候都会新建一个对象
        BinaryOperator<ReportForm> accumulator = (t1, t2) -> {
            ReportForm reportForm = new ReportForm();

            int i1 = t1.getHotLine() == null ? 0 : t1.getHotLine();
            int i2 = t2.getHotLine() == null ? 0 : t2.getHotLine();
            reportForm.setHotLine(i1 + i2);
            int i3 = t1.getOnLine() == null ? 0 : t1.getOnLine();
            int i4 = t2.getOnLine() == null ? 0 : t2.getOnLine();
            reportForm.setOnLine(i3 + i4);
            reportForm.setTime(t1.getTime());

            return reportForm;

        };

        Map<String, Optional<ReportForm>> collect2 = list.stream().collect(Collectors.groupingBy(ReportForm::getTime, Collectors.reducing(accumulator)));

        System.out.println(JSON.toJSONString(collect2, true));


    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    private class ReportForm{

        private String time;

        private Integer hotLine;

        private Integer onLine;

    }
}
