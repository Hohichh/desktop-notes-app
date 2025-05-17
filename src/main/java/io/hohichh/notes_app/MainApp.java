package io.hohichh.notes_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("started");
        Label hello = new Label("Hello, JavaFX!");
        StackPane root = new StackPane(hello);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Desktop Notes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
