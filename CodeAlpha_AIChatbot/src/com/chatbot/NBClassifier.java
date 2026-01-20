package com.chatbot;

import java.util.HashMap;
import java.util.Map;

public class NBClassifier {

    private static final Map<String, Map<String, Integer>> categoryWordCounts = new HashMap<>();
    private static final Map<String, Integer> categoryCounts = new HashMap<>();

    // TRAINING NAIVE BAYES MODEL
    public static void trainModel() {

        train("java", "java language object oriented programming platform independent");
        train("java", "oops inheritance polymorphism abstraction encapsulation");
        train("java", "what is java");

        train("ai", "artificial intelligence machine learning deep learning neural networks");
        train("ai", "what is ai");
        train("ai", "ai use cases and applications");

        train("sql", "sql database tables queries joins primary key foreign key");
        train("sql", "what is sql");
        train("sql", "database management systems relation tables");

        train("general", "hello hi greetings how are you");
        train("general", "bye good night goodbye see you later");
        train("general", "what is your name");
    }

    // TRAIN HELPER
    private static void train(String category, String sentence) {
        categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);

        String[] words = sentence.split(" ");
        Map<String, Integer> wordCountMap = categoryWordCounts.getOrDefault(category, new HashMap<>());

        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        categoryWordCounts.put(category, wordCountMap);
    }

    // PREDICTION (ML PART)
    public static String predict(String input) {
        String[] words = input.split(" ");
        String bestCategory = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (String category : categoryCounts.keySet()) {
            double score = Math.log(categoryCounts.get(category));

            Map<String, Integer> wordCounts = categoryWordCounts.get(category);

            for (String word : words) {
                int count = wordCounts.getOrDefault(word, 0);
                score += Math.log(count + 1);
            }

            if (score > bestScore) {
                bestScore = score;
                bestCategory = category;
            }
        }

        return bestCategory;
    }
}
