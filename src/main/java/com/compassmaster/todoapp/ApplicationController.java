package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ApplicationController {
    @FXML
    public AnchorPane centerStage;

    private ProjectWindowController projectWindowController;
    private TaskWindowController taskWindowController;


    @FXML
    public void initialize(){
        try {
            projectWindowController = new ProjectWindowController();
            taskWindowController = new TaskWindowController();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("project-window.fxml"));
            BorderPane content = fxmlLoader.load();
            centerStage.getChildren().add(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPageContent(String fxmlFile, Object controller){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            fxmlLoader.setController(controller);
            BorderPane content = fxmlLoader.load();
            centerStage.getChildren().set(1, content);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
