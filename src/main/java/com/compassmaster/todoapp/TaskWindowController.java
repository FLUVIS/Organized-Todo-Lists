package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class TaskWindowController {
    @FXML
    public Button addTaskButton, backButton, clearButton;
    @FXML
    public ScrollPane taskBox;
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
}
