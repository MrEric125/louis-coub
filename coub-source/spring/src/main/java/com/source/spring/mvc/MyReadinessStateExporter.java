package com.source.spring.mvc;

import org.springframework.stereotype.Component;

/**
 * @author jun.liu
 * @since 2021/5/18 15:46
 */
@Component
public class MyReadinessStateExporter {


    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {

    }
}
