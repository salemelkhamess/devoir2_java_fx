package com.example.dev2_chat_app;

import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MessageCell extends ListCell<String> {
    @Override
    protected void updateItem(String message, boolean empty) {
        super.updateItem(message, empty);
        if (empty || message == null) {
            setGraphic(null);
            setText(null);
        } else {
            Label messageLabel = new Label(message);
            messageLabel.setWrapText(true);
            messageLabel.setMaxWidth(250);

            HBox container = new HBox();
            if (message.startsWith("Moi: ")) {
                messageLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 5px; -fx-background-radius: 10px;");
                messageLabel.setTextAlignment(TextAlignment.RIGHT);
                container.getChildren().add(messageLabel);
                HBox.setHgrow(messageLabel, Priority.ALWAYS);
                container.setStyle("-fx-alignment: CENTER-RIGHT;");
            } else {
                messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5px; -fx-background-radius: 10px;");
                messageLabel.setTextAlignment(TextAlignment.LEFT);
                container.getChildren().add(messageLabel);
                HBox.setHgrow(messageLabel, Priority.ALWAYS);
                container.setStyle("-fx-alignment: CENTER-LEFT;");
            }
            setGraphic(container);
        }
    }
}
