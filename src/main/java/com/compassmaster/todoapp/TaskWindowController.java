package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
}
