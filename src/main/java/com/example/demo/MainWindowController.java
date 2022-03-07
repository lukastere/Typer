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

    ObservableList<String> list = FXCollections.observableArrayList("Easy", "Medium", "Hard");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myComboBox.setItems(list);
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
        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }
}
