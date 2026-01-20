AI Chatbot (CodeAlpha Internship Project)

📌 Overview
The AI Chatbot is a Java-based interactive chat system designed to simulate human-like conversations.  
It responds to user queries using predefined patterns, basic NLP (Natural Language Processing) logic, and conditional flows.  
The goal of the project is to demonstrate how conversational agents can be built using core Java, without using external AI/ML libraries.

---

🎯 Features
- Engages in interactive dialogue with the user  
- Detects keywords and generates appropriate responses  
- Handles unknown inputs with fallback responses  
- Supports continuous conversation loop  
- Clean, modular design using multiple classes  
- GUI and Console-based interaction (depending on your implementation)

---

🛠 Technologies Used
- Java 
- OOP Concepts (Classes, Objects, Inheritance)  
- Conditional Logic 
- String Manipulation  
- Scanner / GUI Components  

---

⚙️ How the Chatbot Works
1. User enters a message.
2. The system processes the sentence:
   - Detects keywords like hello, help, time, name, etc.
   - Uses NLP-like preprocessing (tokenizing, cleaning text).
3. Based on detected intent, the chatbot selects the appropriate response.
4. If no keyword is found, the bot replies with a default message like:
   > "I'm not sure I understand. Could you please rephrase?"
5. The loop continues until the user types `"exit"`.

---

 📂 Project Structure
- `ChatbotLogic.java` → Contains main chatbot logic and responses  
- `NLPProcessor.java` → Preprocesses user input (optional)  
- `NBClassifier.java` → Additional classification logic (optional)  
- `ChatbotGUI.java` → Graphical user interface (if included)  
- `Main.java` → Entry point of the application  

---

📸 Example Interaction
User:Hello  
Bot: Hi there! How can I assist you today?

User: What’s your name?  
Bot: I am your Java AI Chatbot.

---

📘 Learning Outcome
- Understanding of chatbot logic  
- NLP fundamentals using Java  
- GUI design (Swing/JavaFX if used)  
- Clean, modular coding practices  
- Improved problem-solving skills  

---

🧑‍💻 Developed By
Vishwanatham Poojitha
CodeAlpha Internship — Java Development Track
