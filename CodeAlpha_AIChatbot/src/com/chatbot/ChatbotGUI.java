package com.chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatbotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public ChatbotGUI() {
        // Window Title
        setTitle("AI Chatbot - Java GUI");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Chat Area (shows conversation)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel (input + send button)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sendButton.setBackground(new Color(0, 122, 255));
        sendButton.setForeground(Color.WHITE);

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // ACTION LISTENER (When "Send" is clicked)
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // PRESS ENTER → SEND MESSAGE
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    // Main logic for sending message
    private void sendMessage() {
        String userText = inputField.getText().trim();

        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");

        // NLP processing
        String processed = NLPProcessor.processInput(userText);

        // Chatbot responding
        String botReply = ChatbotLogic.getResponse(processed);
        chatArea.append("Bot: " + botReply + "\n\n");

        inputField.setText("");  // clear input
    }

    public static void main(String[] args) {
        // Start the GUI
        SwingUtilities.invokeLater(() -> {
            ChatbotGUI gui = new ChatbotGUI();
            gui.setVisible(true);
        });
    }
}
