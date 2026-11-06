package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class SettingsWindowController {
    @FXML
    private Button backButton, saveButton;
    @FXML
    private ChoiceBox fontDropdown;
    @FXML
    private RadioButton appStyleHandRadio, appStyleSleekRadio;
    @FXML
    private Spinner fontSizeSpinner;
    @FXML
    private ScrollPane scrollPane;
    private AnchorPane centerStage;

    @FXML
    private void initialize(){
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        centerStage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            if(code == KeyCode.ESCAPE){
                back();
            }
        });

        appStyleHandRadio.setOnAction(event -> {
            selectStyle(0);
        });

        appStyleSleekRadio.setOnAction(event -> {
            selectStyle(1);
        });

        backButton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Random random = new Random();
                backButton.getStyleClass().add("button" + Integer.toString(random.nextInt(5) + 1));
                saveButton.getStyleClass().add("button" + Integer.toString(random.nextInt(5) + 1));
            }
        });
    }


    @FXML
    private void back(){
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

    public void setCenterStage(AnchorPane stage){
        this.centerStage = stage;
    }

    private void selectStyle(int style){
        if(style == 0){
            appStyleSleekRadio.setSelected(false);
        } else {
            appStyleHandRadio.setSelected(false);
        }
    }

    @FXML
    private void saveSettings(){
        System.out.println("Settings Saved");
    }
}

