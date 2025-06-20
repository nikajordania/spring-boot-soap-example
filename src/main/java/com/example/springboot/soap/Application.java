package com.example.springboot.soap;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9093", "-ifNotExists").start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SpringApplication.run(Application.class, args);
    }

}
