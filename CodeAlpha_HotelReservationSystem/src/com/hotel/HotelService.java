package com.hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelService {

    // SEARCH ROOMS
    public List<Room> searchRooms(String type) {

        type = type.trim().toUpperCase();   // ⭐ FIX
        System.out.println("Searching for: [" + type + "]");

        List<Room> rooms = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();

        if (con == null) {
            System.out.println("DB connection failed");
            return rooms;
        }

        try {
            String sql =
                    "SELECT room_id, room_type, price, available " +
                            "FROM rooms WHERE room_type = ? AND available = 1";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getInt("available") == 1
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // BOOK ROOM
    public boolean bookRoom(String name, int roomId, double amount) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String updateRoom =
                    "UPDATE rooms SET available = 0 WHERE room_id = ?";
            PreparedStatement ps1 = con.prepareStatement(updateRoom);
            ps1.setInt(1, roomId);
            ps1.executeUpdate();

            String insert =
                    "INSERT INTO reservations " +
                            "(customer_name, room_id, amount, status) " +
                            "VALUES (?, ?, ?, 'CONFIRMED')";

            PreparedStatement ps2 = con.prepareStatement(insert);
            ps2.setString(1, name);
            ps2.setInt(2, roomId);
            ps2.setDouble(3, amount);
            ps2.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // CANCEL BOOKING
    public boolean cancelBooking(int bookingId) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String getRoom =
                    "SELECT room_id FROM reservations WHERE booking_id = ?";
            PreparedStatement ps = con.prepareStatement(getRoom);
            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return false;

            int roomId = rs.getInt("room_id");

            PreparedStatement ps1 =
                    con.prepareStatement(
                            "UPDATE rooms SET available = 1 WHERE room_id = ?");
            ps1.setInt(1, roomId);
            ps1.executeUpdate();

            PreparedStatement ps2 =
                    con.prepareStatement(
                            "UPDATE reservations SET status='CANCELLED' WHERE booking_id = ?");
            ps2.setInt(1, bookingId);
            ps2.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
