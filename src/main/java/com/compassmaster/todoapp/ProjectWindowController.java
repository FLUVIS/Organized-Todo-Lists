package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectWindowController {
    @FXML
    public Button addProject;
    @FXML
    public ScrollPane projectBox;

    private AddProjectController addProjectController;
    public ArrayList<String> projects;

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
        //clear the list of projects
    }

    public void fillProjects(){
        //fill all of the projects in the project list
    }

}
