package com.compassmaster.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;

public class AddProjectController {
    @FXML
    public TextField projectName;
    @FXML
    public Button addButton;
    public boolean addName;

    @FXML
    public void add(){
        String name = projectName.getText();
        String path = name + ".txt";
        File taskFile = new File(path);
        if(taskFile.exists()){
            // show pop up alert
            addName = false;
        }else{
            try{
                taskFile.createNewFile();
                File projectFile = new File("projects.txt");
                if(!projectFile.exists()){
                    projectFile.createNewFile();
                }
                addToFile("projects.txt", name);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
