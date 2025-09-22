package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class TaskWindowController {
    @FXML
    public Button addTaskButton, backButton, clearButton;
    @FXML
    public VBox taskBox;
    @FXML
    public Text titleBox;
    @FXML
    public TextField taskField;
    public String project;
    public AnchorPane centerStage;

    public void setProject(String p){
        this.project = p;
    }

    public void setCenterStage(AnchorPane stage){
        this.centerStage = stage;
    }

    @FXML
    public void back(){
        try {
            ProjectWindowController projectWindowController = new ProjectWindowController();
            projectWindowController.setCenterStage(centerStage);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("project-window.fxml"));
            fxmlLoader.setController(projectWindowController);
            BorderPane content = fxmlLoader.load();
            centerStage.getChildren().set(0, content);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void addTask(){

    }

    @FXML
    public void clearMarkedTasks(){

    }

    public void clearTaskBox(){
        taskBox.getChildren().clear();
    }
}
