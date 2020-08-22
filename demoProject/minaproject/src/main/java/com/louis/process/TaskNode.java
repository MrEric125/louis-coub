package com.louis.process;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jun.liu
 * @date created on 2020/8/13
 * description:
 */
@Data
public class TaskNode<ID extends Serializable> {

    private ID nextTask;

    private ID preTask;

    private List<Business> businessList;

    private ID taskId;

    public static void main(String[] args) {
        TaskNode taskNode = new TaskNode();
            List<String> collect = Arrays.stream(taskNode.getClass().getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
            System.out.println(collect);
    }




}
