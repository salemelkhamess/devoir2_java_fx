package com.example.dev2_chat_app;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.application.Platform;

public class ReceiverController {
    @FXML private ListView<HBox> chatListView;
    @FXML private TextField messageField;
    private ChatClient client;

    public void initialize() {
        try {
            System.out.println("Tentative de connexion au serveur...");
            client = new ChatClient("localhost", 12345, this::receiveMessage);
            System.out.println("Connecté au serveur !");
        } catch (Exception e) {
            System.err.println("Erreur de connexion au serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void receiveMessage(String message) {
        Platform.runLater(() -> {

                System.out.println("============================================");
                System.out.println(message);
                HBox messageBox = createMessageBubble("Sender: " + message, Pos.BASELINE_LEFT, Color.LIGHTGRAY);
                chatListView.getItems().add(messageBox); // Ajouter le message à la ListView

        });
    }

    @FXML
    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            System.out.println("Envoi du message : " + message);

            client.sendMessage("Moi:" + message);
            HBox messageBox = createMessageBubble("Moi: " + message, Pos.BASELINE_RIGHT, Color.LIGHTBLUE);
            chatListView.getItems().add(messageBox);
            messageField.clear();
        }
    }

    private HBox createMessageBubble(String text, Pos position, Color color) {
        TextFlow textFlow = new TextFlow(new Text(text));
        textFlow.setPadding(new javafx.geometry.Insets(5));
        textFlow.setStyle("-fx-background-color: " + toRgbString(color) + "; -fx-background-radius: 10;");

        HBox container = new HBox(textFlow);
        container.setAlignment(position);
        container.setPadding(new javafx.geometry.Insets(5));

        return container;
    }

    private String toRgbString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}