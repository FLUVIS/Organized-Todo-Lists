package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;

public class ProjectWindowController {
    @FXML
    private VBox projectBox;

    private AddProjectController addProjectController;
    private AnchorPane centerStage;

    public void setCenterStage(AnchorPane stage){
        this.centerStage = stage;
    }

    @FXML
    private void initialize(){
        addProjectController = new AddProjectController();
        clearProjects();
        fillProjects();
    }

    @FXML
    private void addProject(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-project-window.fxml"));
            loader.setController(addProjectController);
            Parent window = loader.load();
            Scene addScene = new Scene(window);
            Stage addStage = new Stage();
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.setScene(addScene);
            addStage.setResizable(false);
            addStage.initStyle(StageStyle.UTILITY);

            addStage.setOnHidden(windowEvent -> {
                if(addProjectController.getAddName()) {
                    clearProjects();
                    fillProjects();
                }
            });

            addProjectController.setAddName(false);
            addStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearProjects(){
        projectBox.getChildren().clear();
    }

    private void fillProjects(){
        try{
            String file = "projects.txt";
            File f = new File(file);
            if(f.exists()){
                BufferedReader br = new BufferedReader(new FileReader(file));
                String project;
                while((project = br.readLine()) != null){
                    createHBox(project);
                }
                br.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void createHBox(String project){
        HBox newBox = new HBox();

        HBox pBox = new HBox();
        Button projectButton = new Button(project);
        projectButton.setStyle("-fx-font-size: 16");
        projectButton.setOnAction(event -> {
            openProject(project);
        });
        pBox.getChildren().add(projectButton);
        pBox.setAlignment(Pos.CENTER_LEFT);
        pBox.setPrefWidth(292);

        HBox dBox = new HBox();
        Button deleteProjectButton = new Button("X");
        deleteProjectButton.setStyle("-fx-font-size: 16");
        deleteProjectButton.setOnAction(event -> {
            verify(project);
        });
        dBox.getChildren().add(deleteProjectButton);
        dBox.setAlignment(Pos.CENTER_RIGHT);
        dBox.setPrefWidth(292);

        newBox.getChildren().addAll(pBox, dBox);
        projectBox.getChildren().add(newBox);
    }

    private void openProject(String project){
        try {
            TaskWindowController taskWindowController = new TaskWindowController();
            taskWindowController.setProject(project);
            taskWindowController.setCenterStage(centerStage);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task-window.fxml"));
            fxmlLoader.setController(taskWindowController);
            BorderPane content = fxmlLoader.load();
            taskWindowController.setTitleBox(project);
            centerStage.getChildren().set(0, content);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void verify(String project){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("verification-window.fxml"));
            VerificationController verificationController = new VerificationController();
            loader.setController(verificationController);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            verificationController.setProject(project);
            verificationController.setStage(stage);
            stage.show();
            stage.setOnHidden(windowEvent -> {
                if(verificationController.getDoDelete()){
                    deleteProject(project);
                    clearProjects();
                    fillProjects();
                }
            });
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deleteProject(String name){
        try {
            String file = name + ".txt";
            ArrayList<String> pList = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("projects.txt"));
            String project;
            while ((project = br.readLine()) != null) {
                if(!project.equals(name)){
                    pList.add(project);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("projects.txt"));
            for(String a: pList){
                bw.write(a + "\n");
            }
            bw.close();

            File taskFile = new File(file);
            taskFile.delete();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
