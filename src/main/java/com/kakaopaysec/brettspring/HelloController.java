package com.kakaopaysec.brettspring;

import java.util.Objects;

public class HelloController {
    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();
        // Objects.requireNonNull -> null 인지 체크
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
