package com.hotel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HotelGUI extends JFrame {

    private HotelService service = new HotelService();

    private JComboBox<String> roomTypeBox;
    private JTextArea searchArea;

    private JTextField nameField;
    private JTextField roomIdField;
    private JTextArea bookArea;

    private JTextField bookingIdField;
    private JTextArea cancelArea;

    public HotelGUI() {
        setTitle("Hotel Reservation System");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Search Rooms", searchPanel());
        tabs.add("Book Room", bookPanel());
        tabs.add("Cancel Booking", cancelPanel());

        add(tabs);
    }

    private JPanel searchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel top = new JPanel();
        roomTypeBox = new JComboBox<>(new String[]{"STANDARD", "DELUXE", "SUITE"});
        JButton searchBtn = new JButton("Search");

        top.add(new JLabel("Room Type:"));
        top.add(roomTypeBox);
        top.add(searchBtn);

        searchArea = new JTextArea();
        searchArea.setEditable(false);

        searchBtn.addActionListener(e -> {
            searchArea.setText("");
            String type =
                    roomTypeBox.getSelectedItem().toString()
                            .trim().toUpperCase();   // ⭐ FIX

            List<Room> rooms = service.searchRooms(type);

            if (rooms.isEmpty()) {
                searchArea.setText("No rooms available.");
                return;
            }

            for (Room r : rooms) {
                searchArea.append(
                        "Room ID: " + r.getRoomId() +
                                " | Price: ₹" + r.getPrice() + "\n");
            }
        });

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(searchArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel bookPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        nameField = new JTextField();
        roomIdField = new JTextField();
        JButton bookBtn = new JButton("Book");

        form.add(new JLabel("Customer Name:"));
        form.add(nameField);
        form.add(new JLabel("Room ID:"));
        form.add(roomIdField);
        form.add(bookBtn);

        bookArea = new JTextArea();
        bookArea.setEditable(false);

        bookBtn.addActionListener(e -> {
            try {
                int roomId = Integer.parseInt(roomIdField.getText());
                double price = getPrice(roomId);

                boolean success =
                        service.bookRoom(nameField.getText(), roomId, price);

                bookArea.setText(success ?
                        "Booking Successful!" :
                        "Booking Failed!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(bookArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel cancelPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        bookingIdField = new JTextField();
        JButton cancelBtn = new JButton("Cancel");

        form.add(new JLabel("Booking ID:"));
        form.add(bookingIdField);
        form.add(cancelBtn);

        cancelArea = new JTextArea();
        cancelArea.setEditable(false);

        cancelBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(bookingIdField.getText());
                boolean success = service.cancelBooking(id);
                cancelArea.setText(success ?
                        "Booking Cancelled!" :
                        "Invalid Booking ID!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(cancelArea), BorderLayout.CENTER);
        return panel;
    }

    private double getPrice(int roomId) {
        if (roomId == 101 || roomId == 102) return 2000;
        if (roomId == 201 || roomId == 202) return 3500;
        if (roomId == 301) return 6000;
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new HotelGUI().setVisible(true));
    }
}
