package io.hohichh.notesapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("started");
        Label hello = new Label("Hello, JavaFX!");
        StackPane root = new StackPane(hello);
        // Загружаем один или несколько стилей шрифта
        Font.loadFont(getClass().getResourceAsStream("/fonts/Montserrat-Regular.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Montserrat-Bold.ttf"), 16);
        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Desktop Notes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
