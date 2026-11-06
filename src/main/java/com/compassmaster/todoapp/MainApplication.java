package com.compassmaster.todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javafx.stage.StageStyle.DECORATED;

public class MainApplication extends Application {
    ApplicationController applicationController;

    private static double x, y;

    private static List<String> fontList;


    @Override
    public void start(Stage stage) throws IOException {
        //Load the font
        Font f = Font.loadFont(getClass().getResourceAsStream("/com/compassmaster/todoapp/fonts/hand.ttf"), 10);
        System.out.println("Loaded font: " + f.getName());
        setFontList();

        //Initialize the main stage
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-stage.fxml"));
        applicationController = new ApplicationController();
        fxmlLoader.setController(applicationController);
        Scene scene = new Scene(fxmlLoader.load(), 630, 750);
        scene.getStylesheets().add(getClass().getResource("/com/compassmaster/todoapp/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(DECORATED);
        stage.setTitle("To Do");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/compassmaster/todoapp/images/Desktop Icon.png"))));

        //Add x and y listeners
        stage.xProperty().addListener((o, oldVal, newVal) -> {
            x = newVal.doubleValue();
        });
        stage.yProperty().addListener((o, oldVal, newVal) -> {
            y = newVal.doubleValue();
        });

        //Open the window
        stage.show();
    }

    private static void setFontList(){
        ArrayList<String> arrayList = new ArrayList<>(Font.getFamilies());
        arrayList.addFirst("Hand Drawn");
        fontList = arrayList;
    }

    public static double getX(){
        return x;
    }

    public static double getY(){
        return y;
    }

    public static List<String> getFontList(){
        return fontList;
    }

    public static void main(String[] args) {
        launch();
    }
}
