package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ApplicationController {
    @FXML
    private Button minimize, close;
    private AnchorPane centerStage;

    private ProjectWindowController projectWindowController;
    private TaskWindowController taskWindowController;


    @FXML
    public void initialize(){

    }
}
