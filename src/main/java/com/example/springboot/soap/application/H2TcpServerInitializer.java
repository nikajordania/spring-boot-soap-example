package com.example.springboot.soap.application;

import org.h2.tools.Server;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.Socket;
import java.sql.SQLException;

public class H2TcpServerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final int H2_PORT = 9093;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (!isPortInUse(H2_PORT)) {
            try {
                Server.createTcpServer(
                        "-tcp", "-tcpAllowOthers", "-tcpPort", String.valueOf(H2_PORT), "-ifNotExists"
                ).start();
                System.out.println("✅ H2 TCP server started on port " + H2_PORT);
            } catch (SQLException e) {
                throw new RuntimeException("❌ Failed to start H2 TCP server on port " + H2_PORT, e);
            }
        } else {
            System.out.println("⚠️ H2 TCP server already running on port " + H2_PORT + " — skipping startup.");
        }
    }

    private boolean isPortInUse(int port) {
        try (Socket socket = new Socket("localhost", port)) {
            return true; // Port is open
        } catch (Exception ex) {
            return false; // Port is closed
        }
    }
}
