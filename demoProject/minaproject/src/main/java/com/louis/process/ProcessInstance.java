package com.louis.process;

import java.util.List;

/**
 * @author jun.liu
 * @date created on 2020/8/13
 * description:
 */
public interface ProcessInstance {

    String getProcessDefinitionId();

    String getProcessDefinitionName();

    String getProcessDefinitionKey();

    String getProcessDefinitionVersion();

    List<Business> getBusinessKeys();

    boolean isSuspended();

    String getName();

    List<TaskNode> getTaskList();












}
