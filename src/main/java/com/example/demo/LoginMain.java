//previously from Kristaps git called Main
package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            stage.setTitle("Log into Typer!");
            stage.setScene(new Scene(root));
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}