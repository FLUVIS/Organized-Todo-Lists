package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class AddProjectController {
    @FXML
    public TextField projectName;
    @FXML
    public Button addButton;
    public boolean addName;

    @FXML
    public void add(){
        try {
            String name = projectName.getText();
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
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAddName(boolean b){
        this.addName = b;
    }

    public void addToFile(String path, String name){
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

    public void alert(){
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
