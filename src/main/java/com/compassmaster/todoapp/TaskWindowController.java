package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TaskWindowController {
    @FXML
    private Button backButton, addTaskButton, clearButton;
    @FXML
    private Text titleBox;
    @FXML
    private TextField taskField;
    @FXML
    private ScrollPane taskBox;
}
