package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ApplicationController {
    @FXML
    public AnchorPane centerStage;


    @FXML
    public void initialize(){
        try {
            ProjectWindowController projectWindowController = new ProjectWindowController();
            projectWindowController.setCenterStage(centerStage);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("project-window.fxml"));
            fxmlLoader.setController(projectWindowController);
            BorderPane content = fxmlLoader.load();
            centerStage.getChildren().add(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
