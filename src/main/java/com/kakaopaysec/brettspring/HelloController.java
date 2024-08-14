package com.kakaopaysec.brettspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/* @RestController
    dispatcherServlet 과는 연관 없음
    class 에 하위 메소드들에 대해 @ResponseBody를 붙인 것과 같은 동작을 하게 해줌
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

//    @RequestMapping(name = "/hello", method = RequestMethod.GET)
//    @GetMapping("/hello") // 위와 동일
//    @ResponseBody // 어노테이션을 붙여줌으로써 View가 아닌 String 으로 응답을 인식할 수 있음
    @GetMapping
    public String hello(String name) {
        // Objects.requireNonNull -> null 인지 체크
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
