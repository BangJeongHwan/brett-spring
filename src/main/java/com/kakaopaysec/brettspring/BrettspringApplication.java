package com.kakaopaysec.brettspring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan  // @Component 로 등록된 모든 클래스를 Bean 으로 등록시켜준다.
public class BrettspringApplication {
    public static void main(String[] args) {
        // GenericWebApplicationContext 템플릿 메소드 패턴 : 상속을 통해 기능을 확장
        // onRefresh : 스프링 컨테이너를 초기화 하던 중에 부가적으로 작업을 수행할 때 사용
        // AnnotationConfigApplicationContext : @Bean 을 읽어오기 위해서 변경
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            // 익명 클래스 사용
            @Override
            protected void onRefresh() {
                super.onRefresh();  // 다른 초기화도 필요하기 때문에 냅둠

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();    // tomcat 말고도 사용 가능
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");    // servlet 추가할 때 container 가 어느 servlet 과 mapping 할지를 결정
                });
                webServer.start();

            }
        };
        applicationContext.register(BrettspringApplication.class);  // 클래스를 등록시켜줌
        applicationContext.refresh();   // bean 초기화
    }
}
