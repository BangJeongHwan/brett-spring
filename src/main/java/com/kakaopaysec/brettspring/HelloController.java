package com.kakaopaysec.brettspring;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        // Objects.requireNonNull -> null 인지 체크
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
