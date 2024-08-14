package com.kakaopaysec.brettspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class BrettspringApplication {
    public static void main(String[] args) {
//        GenericApplicationContext applicationContext = new GenericApplicationContext();
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        // 등록 순서는 중요하지 않다.
        applicationContext.registerBean(HelloController.class); // spring container bean 등록
        applicationContext.registerBean(SimpleHelloService.class); // spring container bean 등록
        applicationContext.refresh();   // bean 초기화

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();    // tomcat 말고도 사용 가능
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                        new DispatcherServlet(applicationContext)
                    ).addMapping("/*");    // servlet 추가할 때 container 가 어느 servlet 과 mapping 할지를 결정
        });
        webServer.start();
    }
}


