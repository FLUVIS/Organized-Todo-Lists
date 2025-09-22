package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    public Button addProject;
    @FXML
    public VBox projectBox;

    private AddProjectController addProjectController;
    public AnchorPane centerStage;

    @FXML
    public void initialize(){
        addProjectController = new AddProjectController();
        clearProjects();
        fillProjects();
    }

    @FXML
    public void addProject(){
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
                if(addProjectController.addName == true) {
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

    public void clearProjects(){
        projectBox.getChildren().clear();
    }

    public void fillProjects(){
        String file = "projects.txt";
        File f = new File(file);
        if(f.exists()){
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String project;
                while((project = br.readLine()) != null){
                    HBox newBox = new HBox();
                    newBox.setId(project);
                    Button projectButton = new Button(project);
                    projectButton.setOnAction(event-> {
                        try {
                            String name = projectButton.getText();
                            TaskWindowController taskWindowController = new TaskWindowController();
                            taskWindowController.setProject(name);
                            taskWindowController.setCenterStage(centerStage);
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task-window.fxml"));
                            fxmlLoader.setController(taskWindowController);
                            BorderPane content = fxmlLoader.load();
                            centerStage.getChildren().set(0, content);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    });
                    Button deleteProjectButton = new Button("X");
                    deleteProjectButton.setId(project + "d");
                    deleteProjectButton.setOnAction(event -> {
                            String temp = deleteProjectButton.getId();
                            String name = temp.substring(0, temp.length()-1);
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
                                verificationController.setProject(name);
                                verificationController.setStage(stage);
                                stage.show();
                                stage.setOnHidden(windowEvent -> {
                                    if(verificationController.doDelete){
                                        deleteProject(name);
                                        clearProjects();
                                        fillProjects();
                                    }
                                });
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                    });
                    newBox.getChildren().addAll(projectButton, deleteProjectButton);

                    projectBox.getChildren().add(newBox);
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void setCenterStage(AnchorPane stage){
        this.centerStage = stage;
    }

    public void deleteProject(String name){
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

            File f = new File("projects.txt");
            f.delete();
            f.createNewFile();

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
