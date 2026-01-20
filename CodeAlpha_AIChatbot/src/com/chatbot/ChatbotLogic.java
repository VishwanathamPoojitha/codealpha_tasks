package com.chatbot;

import java.util.HashMap;
import java.util.Map;

public class ChatbotLogic {

    private static final Map<String, String> faqDatabase = new HashMap<>();

    static {
        faqDatabase.put("java", "Java is an object-oriented programming language used to build applications.");
        faqDatabase.put("ai", "AI enables machines to simulate human intelligence.");
        faqDatabase.put("sql", "SQL is used to manage and query relational databases.");
        faqDatabase.put("general", "I'm here to help! Ask me anything.");
        faqDatabase.put("hello", "Hello! How can I assist you?");
        faqDatabase.put("bye", "Goodbye! Have a great day.");
    }

    // MAIN RESPONSE METHOD
    public static String getResponse(String processedInput) {

        // 1. ML PREDICTION (Naive Bayes)
        String predictedCategory = NBClassifier.predict(processedInput);

        if (predictedCategory != null && faqDatabase.containsKey(predictedCategory)) {
            return faqDatabase.get(predictedCategory);
        }

        // 2. RULE-BASED FALLBACK
        for (String key : faqDatabase.keySet()) {
            if (processedInput.contains(key)) {
                return faqDatabase.get(key);
            }
        }

        return "I'm not sure about that. Could you rephrase your question?";
    }
}
