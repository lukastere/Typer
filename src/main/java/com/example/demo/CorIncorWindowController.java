package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CorIncorWindowController implements Initializable {
    @FXML
    private Button BackToGame;
    @FXML
    private Label Cor;
    @FXML
    private Label InCor;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }
    public void mySecFunction(String inCorr){
        InCor.setText(inCorr);
    }

    public void myFunction(String Corr){
        Cor.setText(Corr);
    }


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (dataCorIncor.text.equals("")){
//            Cor.setText("hi");
//        }else {
//            Cor.setText(dataCorIncor.text);
//            dataCorIncor.text = "";
//        }
//    }




    public void BackToGame(ActionEvent event){





        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Log into Typer!");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



}
