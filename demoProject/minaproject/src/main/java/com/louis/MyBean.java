package com.louis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1212)
class MyBean implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(args);

    }
}
