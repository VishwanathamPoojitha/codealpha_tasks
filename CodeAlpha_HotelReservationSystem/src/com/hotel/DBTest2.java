package com.hotel;

import java.sql.*;

public class DBTest2 {
    public static void main(String[] args) {
        try {
            Connection con = DatabaseConnection.getConnection();
            System.out.println("Connected: " + (con != null));

            // Test INSERT
            String sql = "INSERT INTO reservations (customer_name, room_id, amount, status) VALUES ('TestUser', 101, 2000, 'CONFIRMED')";
            PreparedStatement ps = con.prepareStatement(sql);
            int rows = ps.executeUpdate();

            System.out.println("Rows inserted = " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
