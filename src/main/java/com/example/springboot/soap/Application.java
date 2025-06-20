package com.example.springboot.soap;

import com.example.springboot.soap.application.H2TcpServerInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addInitializers(new H2TcpServerInitializer());
        app.run(args);
    }

}
