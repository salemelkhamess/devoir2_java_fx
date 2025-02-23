package com.example.dev2_chat_app;

import javafx.application.Platform;
import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private MessageListener listener;

    public ChatClient(String host, int port, MessageListener listener) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.listener = listener;

        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Message reçu du serveur : " + message);
                    String finalMessage = message;
                    Platform.runLater(() -> listener.onMessageReceived(finalMessage));
                }
            } catch (IOException e) {
                System.err.println("Erreur de lecture du serveur : " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
    public void sendMessage(String message) {
        out.println(message);
    }

    public interface MessageListener {
        void onMessageReceived(String message);
    }
}