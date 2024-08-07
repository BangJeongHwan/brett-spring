package com.kakaopaysec.brettspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class BrettspringApplication {
    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();    // tomcat 말고도 사용 가능
        WebServer webServer = serverFactory.getWebServer();
        webServer.start();
    }
}


