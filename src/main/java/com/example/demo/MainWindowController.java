package com.example.demo;
//Previously from lukas git called MainController
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    public ComboBox<String> myComboBox;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button buttonStart;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Button stButton;

    static String x;


    ObservableList<String> list = FXCollections.observableArrayList("Easy", "Medium", "Hard");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myComboBox.setItems(list);
        welcomeLabel.setText(x);


    }


    //maybe this solves the problem
    public void getUser(String text) {
        x = text;
        welcomeLabel.setText(text);
        System.out.println("You entered main window user : "+ welcomeLabel.getText());
    }


    public void buttonImportAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        // Adding import text from .txt files
        // At the moment it adds text to listView later need to change it
        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()))) {

                String line;
                while ((line = reader.readLine()) != null)
                    listView.getItems().addAll(line);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File is not valid");
        }
    }

    public void buttonLeaderboard(ActionEvent event) throws IOException {
        FXMLLoader leaderboards = new FXMLLoader(getClass().getResource("Leaderboards.fxml"));
        Scene scene = new Scene(leaderboards.load(), 600, 400);
        Stage stage = new Stage();
        LeaderboardController LeaderboardController = leaderboards.getController();
        LeaderboardController.getUser(welcomeLabel.getText());

        System.out.println(welcomeLabel.getText());

        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }
    public void buttonWebscrape() throws IOException{
        FXMLLoader leaderboards = new FXMLLoader(getClass().getResource("Webscrape.fxml"));
        Scene scene = new Scene(leaderboards.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }

    public void buttonStart(ActionEvent event) throws IOException{
        FXMLLoader leaderboards = new FXMLLoader(getClass().getResource("MainGame.fxml"));
        Scene scene = new Scene(leaderboards.load(), 600, 400);
        Stage stage = new Stage();
        GameController GameController = leaderboards.getController();
        System.out.println("You pressed maingame " + welcomeLabel.getText());
        welcomeLabel.setText(welcomeLabel.getText());
        GameController.getUser(welcomeLabel.getText());
        System.out.println(welcomeLabel.getText());

        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }

    public void buttonLabel(ActionEvent event) throws IOException{

        System.out.println("button pressed " + welcomeLabel.getText());
    }

}
