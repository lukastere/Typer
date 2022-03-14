//login controller, and also hello-view
//previously from Kristaps git called Controller
package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginError;

    @FXML
    private ImageView loginImage;

    @FXML
    private ImageView logoImage;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    // Loads the images
    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File loginFile = new File("images/login.jpg");
        Image loginPicture = new Image(loginFile.toURI().toString());
        loginImage.setImage(loginPicture);

        File typerFile = new File("images/typer.png");
        Image typerPicture = new Image(typerFile.toURI().toString());
        logoImage.setImage(typerPicture);
    }

    // moves onto register window
    public void registerWindow(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register-view.fxml"));
            Stage registerStage = new Stage();
            registerStage.setTitle("Registration form!");
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loginButtonOnAction(ActionEvent event) {

        //if fields are not blank then it calls validateLogin method
        if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginError.setText("Please enter username and password");
        }
    }

    // Closes the window when cancel button is pressed
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE username = '" + username.getText() + "'AND password ='" + password.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginError.setText("You have logged in!");
                  //  String user = username.getText();
                    //calls register view
                   // String user = username.getText();
                    mainMenuWindow();
                } else {
                    loginError.setText("Invalid login, please try again!");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }




    public void mainMenuWindow() {


        try {
            //String user = username.getText();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = (Parent) loader.load();



            MainWindowController mainWindowController = loader.getController();
            mainWindowController.myTfunct(username.getText());


            Stage stage = new Stage();
            stage.setTitle("Log into Typer!");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}