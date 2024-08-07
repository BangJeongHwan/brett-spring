package com.kakaopaysec.brettspring;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class BrettspringApplication {
    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();    // tomcat 말고도 사용 가능
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {

                final HelloController helloController = new HelloController();  // mapping

                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통 기능
                    if(
                        req.getRequestURI().equals("/hello") &&
                                req.getMethod().equals(HttpMethod.GET.name())
                    ) {
                        String name = req.getParameter("name");

                        String hello = helloController.hello(name);

                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(hello);
                    } else if(req.getRequestURI().equals("/user")) {
                        //
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");    // servlet 추가할 때 container 가 어느 servlet 과 mapping 할지를 결정
        });
        webServer.start();
    }
}


