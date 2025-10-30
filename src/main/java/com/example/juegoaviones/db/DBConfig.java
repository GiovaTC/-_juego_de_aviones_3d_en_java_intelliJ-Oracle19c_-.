package com.example.juegoaviones.db;

import java.net.URL;

public class DBConfig {
    public static final String URL  = System.getenv().getOrDefault("JDBC_URL", "jdbc:oracle:thin:@//localhost:1521/orcl");
    public static final String USER = System.getenv().getOrDefault("JDBC_USER", "system");
    public static final String PASS = System.getenv().getOrDefault("JDBC_PASSWORD", "Tapiero123");

    public static void printConfig() {
        System.out.println("===== Configuración JDBC Actual =====");
        System.out.println("URL: " + URL);
        System.out.println("USER: " + USER);
        System.out.println("PASS: [OCULTA]");
        System.out.println("=====================================");
    }
}
