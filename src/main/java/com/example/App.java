package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://DESKTOP-GMUPP2V\\SQLEXPRESS:64577;databaseName=master;integratedSecurity=true";

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
            server.createContext("/DameFechaHora", new DameFechaHoraHandler(connectionUrl));
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Servidor HTTP iniciado en el puerto 8081");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class DameFechaHoraHandler implements HttpHandler {
        private String connectionUrl;

        public DameFechaHoraHandler(String connectionUrl) {
            this.connectionUrl = connectionUrl;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try (Connection con = DriverManager.getConnection(connectionUrl)) {
                try (Statement stmt = con.createStatement()) {
                    ResultSet rs = stmt.executeQuery("SELECT GETDATE()");
                    if (rs.next()) {
                        String fechaHora = rs.getTimestamp(1).toString();
                        exchange.sendResponseHeaders(200, fechaHora.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(fechaHora.getBytes());
                        os.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
