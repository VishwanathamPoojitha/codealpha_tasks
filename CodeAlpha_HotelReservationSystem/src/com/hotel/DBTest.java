package com.hotel;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {

        System.out.println("Trying to connect...");

        Connection con = DatabaseConnection.getConnection();

        if (con == null) {
            System.out.println("❌ Database NOT connected");
        } else {
            System.out.println("✅ Database connected successfully!");
        }
    }
}
