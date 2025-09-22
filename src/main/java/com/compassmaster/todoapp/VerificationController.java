package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VerificationController {
    @FXML
    public Button yesButton, noButton;
    @FXML
    public Label project;
    public boolean doDelete;
    public Stage stage;

    @FXML
    public void initialize(){
        setDoDelete(false);
    }

    public void setProject(String name){
        project.setText(name);
    }

    public void setStage(Stage s){
        this.stage = s;
    }

    @FXML
    public void yes(){
        doDelete = true;
        stage.close();
    }

    @FXML
    public void no(){
        doDelete = false;
        stage.close();
    }

    public void setDoDelete(boolean b){
        this.doDelete = b;
    }
}
