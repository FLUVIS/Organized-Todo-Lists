package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;

public class TaskWindowController {
    @FXML
    public Button addTaskButton, backButton, clearButton;
    @FXML
    public VBox taskBox;
    @FXML
    public Text titleBox;
    @FXML
    public TextField taskField;
    public String project;
    public AnchorPane centerStage;

    public void setProject(String p){
        this.project = p;
    }

    public void setCenterStage(AnchorPane stage){
        this.centerStage = stage;
    }

    public void setTitleBox(String t){
        titleBox.setText(t);
    }

    @FXML
    public void back(){
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

    @FXML
    public void addTask(){
        PrintWriter out = null;
        try {
            String task = taskField.getText();
            BufferedWriter bw = new BufferedWriter(new FileWriter(project + ".txt", true));
            out = new PrintWriter(bw);
            out.write(task);
            out.close();
            taskField.setText("");
            clearTaskBox();
            fillTaskBox();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @FXML
    public void clearMarkedTasks(){

    }

    public void clearTaskBox(){
        taskBox.getChildren().clear();
    }

    public void fillTaskBox(){
        /*
        String path = project + ".txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                String task = line.substring(0, line.length()-1);



                HBox newBox = new HBox();
                Label newTask = new Label();
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

         */
    }
}
