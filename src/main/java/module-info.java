module com.example.dev2_chat_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dev2_chat_app to javafx.fxml;
    exports com.example.dev2_chat_app;
}