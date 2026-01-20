package com.chatbot;

public class NLPProcessor {

    // NLP Preprocessing
    public static String processInput(String input) {

        // Convert to lowercase
        input = input.toLowerCase();

        // Remove special characters & numbers
        input = input.replaceAll("[^a-zA-Z ]", "");

        // Remove extra spaces
        input = input.trim().replaceAll("\\s+", " ");

        return input;
    }
}
