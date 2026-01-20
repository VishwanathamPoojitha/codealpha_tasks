package com.hotel;

public class Room {

    private int roomId;
    private String roomType;
    private double price;
    private boolean available;

    public Room(int roomId, String roomType, double price, boolean available) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.available = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
}
