package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VerificationController {
    @FXML
    private Label project;
    private boolean doDelete;
    private Stage stage;

    @FXML
    private void initialize(){
        setDoDelete(false);
    }

    public void setProject(String name){
        this.project.setText(name);
    }

    public void setStage(Stage s){
        this.stage = s;
    }

    public void setDoDelete(boolean b){
        this.doDelete = b;
    }

    public boolean getDoDelete(){
        return this.doDelete;
    }

    @FXML
    private void yes(){
        doDelete = true;
        stage.close();
    }

    @FXML
    private void no(){
        doDelete = false;
        stage.close();
    }
}
