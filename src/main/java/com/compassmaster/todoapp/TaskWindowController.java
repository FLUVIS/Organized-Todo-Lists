package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;

public class TaskWindowController {
    @FXML
    public VBox taskBox;
    @FXML
    public Text titleBox;
    @FXML
    public TextField taskField;
    public String project;
    public AnchorPane centerStage;

    @FXML
    public void initialize(){
        clearTaskBox();
        fillTaskBox();
    }

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
            if(!task.isEmpty()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(project + ".txt", true));
                out = new PrintWriter(bw);
                out.write(task + "0" + "\n");
                out.close();
                taskField.setText("");
                clearTaskBox();
                fillTaskBox();
            }
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
        try {
            ArrayList<String> list = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(project + ".txt"));
            String task;
            while((task = br.readLine()) != null){
                char state = task.charAt(task.length()-1);
                if(state == '0'){
                    list.add(task);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(project + ".txt"));
            for(String a: list){
                bw.write(a + "\n");
            }
            bw.close();

            clearTaskBox();
            fillTaskBox();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clearTaskBox(){
        taskBox.getChildren().clear();
    }

    public void fillTaskBox(){
        String path = project + ".txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                String task = line.substring(0, line.length()-1);
                char state = line.charAt(line.length()-1);
                createTask(task, state);
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void createTask(String task, char state){
        HBox newBox = new HBox();
        Text name = new Text(task);
        CheckBox box = new CheckBox();
        newBox.getChildren().addAll(name,box);
        if(state == '0'){
            box.setSelected(false);
        }else{
            box.setSelected(true);
        }
        if(box.isSelected()){
            name.setStyle("-fx-strikethrough: true;");
        } else{
            name.setStyle("-fx-strikethrough: false;");
        }
        box.setOnAction(ActionEvent -> {
            if(box.isSelected()){
                name.setStyle("-fx-strikethrough: true;");
            } else{
                name.setStyle("-fx-strikethrough: false;");
            }
            updateState(box.isSelected(), task);
        });
        taskBox.getChildren().add(newBox);
    }

    public void updateState(boolean newState, String task){
        ArrayList<String> list = new ArrayList<String>();
        char state = '0';
        char cState = '1';
        if(newState){
            state = '1';
            cState = '0';
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(project + ".txt"));
            String t;
            while((t = br.readLine()) != null){
                if(t.equals(task + cState)){
                    list.add(task + state);
                } else {
                    list.add(t);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(project + ".txt"));
            for(String s: list){
                bw.write(s + "\n");
            }
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
