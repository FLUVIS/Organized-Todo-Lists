package com.compassmaster.todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;

import static javafx.stage.StageStyle.UTILITY;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Load the font
        Font f = Font.loadFont(getClass().getResourceAsStream("/com/compassmaster/todoapp/fonts/hand.ttf"), 10);
        System.out.println("Loaded font: " + f.getName());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-stage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 750);
        scene.getStylesheets().add(getClass().getResource("/com/compassmaster/todoapp/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(UTILITY);
        stage.setTitle("To Do");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
