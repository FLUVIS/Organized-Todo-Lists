package com.compassmaster.todoapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.controlsfx.control.SearchableComboBox;

import java.io.*;
import java.util.Random;

public class SettingsWindowController {
    @FXML
    private Button backButton, saveButton;
    @FXML
    private SearchableComboBox<String> fontDropdown;
    @FXML
    private RadioButton appStyleHandRadio, appStyleSleekRadio;
    @FXML
    private Spinner<Integer> fontSizeSpinner;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label fontSizeTest;
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

        fontDropdown.setItems(FXCollections.observableList(MainApplication.getFontList()));

        //TODO: set the third number in the factory to the current settings number
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(11,96,12);
        fontSizeSpinner.setValueFactory(factory);
        fontSizeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            fontSizeTest.setStyle("-fx-font-size: " + newValue + "px;");
        });
        //TODO: Populate the page with the current settings

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

