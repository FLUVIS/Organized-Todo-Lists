package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Random;

public class AddProjectController {
    @FXML
    private TextField projectName;
    @FXML
    private Button addButton;
    private boolean addName;

    public void setAddName(boolean b){
        this.addName = b;
    }

    public boolean getAddName(){
        return this.addName;
    }

    @FXML
    private void initialize(){
        addButton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                    KeyCode code = keyEvent.getCode();
                    if(code == KeyCode.ENTER){
                        add();
                    } else if (code == KeyCode.ESCAPE){
                        Stage stage = (Stage) addButton.getScene().getWindow();
                        stage.close();
                    }
                });

                Random random = new Random();
                addButton.getStyleClass().add("button" + Integer.toString(random.nextInt(5) + 1));
            }
        });
    }

    @FXML
    private void add(){
        try {
            String name = projectName.getText();
            if(!name.isEmpty()) {
                String path = name + ".txt";
                File taskFile = new File(path);
                File projectFile = new File("projects.txt");
                if (!projectFile.exists()) {
                    projectFile.createNewFile();
                }
                if (taskFile.exists()) {
                    alert();
                    addName = false;
                } else {
                    taskFile.createNewFile();
                    addToFile("projects.txt", name);
                    projectName.setText("");
                    addName = true;
                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToFile(String path, String name){
        PrintWriter out = null;
        try {
            String text = name + "\n";
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.write(text);
            out.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private void alert(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("duplicate-error.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);

            root.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY) {
                    stage.close();
                }
            });

            stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    stage.close();
                }
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
