package com.source.spring.mvc;

import org.springframework.context.ApplicationEvent;

/**
 * @author Louis
 * @date created on 2020/11/28
 * description:
 */

public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }
}
