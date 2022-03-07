package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Statement;

public class RegisterController {
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label passwordConfirmationLabel;


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confPassword;

    public void registerButtonOnAction(ActionEvent event) {

        if (password.getText().equals(confPassword.getText())) {
            registerUser();
            passwordConfirmationLabel.setText("");

        } else {
            passwordConfirmationLabel.setText("Passwords does not match!");

        }

    }
    //closes application
    public void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }


    public void goBackButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Log into Typer!");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String user = username.getText();
        String passw = password.getText();

        // checks if fields are blank
        if (user.isBlank() || passw.isBlank()) {
            registrationMessageLabel.setText("Some fields are blank!");
        } else {

            String insertFields = "INSERT INTO users (username, password) VALUES ('";
            String insertValues = user + "','" + passw + "')";
            String insertToRegister = insertFields + insertValues;

            try {
                Statement statement = connectionDB.createStatement();
                statement.executeUpdate(insertToRegister);
                registrationMessageLabel.setText("User registered successfully!");

            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
            }
        }

    }


}
