package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;

public class TaskWindowController {
    @FXML
    private VBox taskBox;
    @FXML
    private Text titleBox;
    @FXML
    private TextField taskField;
    private String project;
    private AnchorPane centerStage;

    @FXML
    private void initialize(){
        clearTaskBox();
        fillTaskBox();

        centerStage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            if(code == KeyCode.ENTER){
                addTask();
            } else if (code == KeyCode.ESCAPE) {
                back();
            }
        });
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

    @FXML
    private void addTask(){
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
    private void clearMarkedTasks(){
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

    private void clearTaskBox(){
        taskBox.getChildren().clear();
    }

    private void fillTaskBox(){
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

    private void createTask(String task, char state){
        HBox newBox = new HBox(10);
        newBox.setAlignment(Pos.CENTER_LEFT);

        Text text = new Text(task);
        text.setFont(Font.font(16));

        CheckBox box = new CheckBox();
        box.setPrefSize(16, 16);

        newBox.getChildren().addAll(text, box);


        if(state == '0'){
            box.setSelected(false);
        }else{
            box.setSelected(true);
        }
        if(box.isSelected()){
            text.setStyle("-fx-strikethrough: true;");
        } else{
            text.setStyle("-fx-strikethrough: false;");
        }
        box.setOnAction(ActionEvent -> {
            if(box.isSelected()){
                text.setStyle("-fx-strikethrough: true;");
            } else{
                text.setStyle("-fx-strikethrough: false;");
            }
            updateState(box.isSelected(), task);
        });
        taskBox.getChildren().add(newBox);
    }

    private void updateState(boolean newState, String task){
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
