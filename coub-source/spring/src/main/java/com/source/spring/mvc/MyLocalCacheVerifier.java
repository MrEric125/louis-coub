package com.source.spring.mvc;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author jun.liu
 * @since 2021/5/18 15:48
 */
@Component
public class MyLocalCacheVerifier {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MyLocalCacheVerifier(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void checkLocalCache() {
        try {

        } catch (Exception e) {
            AvailabilityChangeEvent.publish(applicationEventPublisher);

        }
    }
}
