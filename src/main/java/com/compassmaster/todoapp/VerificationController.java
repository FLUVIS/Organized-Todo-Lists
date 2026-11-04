package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class VerificationController {
    @FXML
    private Button yesButton, noButton;
    @FXML
    private Label project;
    private boolean doDelete;
    private Stage stage;

    @FXML
    private void initialize(){
        setDoDelete(false);

        project.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                    KeyCode code = keyEvent.getCode();
                    if(code == KeyCode.ENTER){
                        yes();
                    } else if (code == KeyCode.ESCAPE){
                        no();
                    }
                });

                yesButton.getStyleClass().add("yesButton");
                noButton.getStyleClass().add("noButton");
            }
        });
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
