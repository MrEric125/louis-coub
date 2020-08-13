package com.louis.process;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

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




}
