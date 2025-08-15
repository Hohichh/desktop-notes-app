package io.hohichh.notesapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        System.out.println("started");
        Label hello = new Label("Hello, JavaFX!");
        BorderPane root = new BorderPane(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")));
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
