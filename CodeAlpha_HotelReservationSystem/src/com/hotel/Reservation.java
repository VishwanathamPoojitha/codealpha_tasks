package com.hotel;

public class Reservation {

    private int bookingId;
    private String customerName;
    private int roomId;
    private double amount;
    private String status;

    public Reservation(int bookingId, String customerName,
                       int roomId, double amount, String status) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomId = roomId;
        this.amount = amount;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRoomId() {
        return roomId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
