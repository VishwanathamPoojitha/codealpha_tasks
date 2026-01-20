package com.chatbot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // TRAIN ML MODEL
        NBClassifier.trainModel();

        System.out.println("🤖 AI Chatbot Started (type 'exit' to stop)");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Bot: Goodbye!");
                break;
            }

            String processed = NLPProcessor.processInput(userInput);
            String response = ChatbotLogic.getResponse(processed);

            System.out.println("Bot: " + response);
        }

        scanner.close();
    }
}
