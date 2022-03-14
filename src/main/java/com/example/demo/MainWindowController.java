package com.example.demo;
//Previously from lukas git called MainController
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {



    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public ComboBox<String> myComboBox;
    @FXML
    public Label username;
    @FXML public Button startButton;

    @FXML public Button logout;
    ObservableList<String> list = FXCollections.observableArrayList("Import/Scrape","Easy", "Medium", "Hard");


    static String x;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myComboBox.setItems(list);
        startButton.setDisable(true);
    }


    public void comboBox(ActionEvent event){
        if(myComboBox.getValue().equals("Import/Scrape") || myComboBox.getValue().equals("Easy") || myComboBox.getValue().equals("Medium") || myComboBox.getValue().equals("Hard")){
            startButton.setDisable(false);
        }
    }



    public void logout(ActionEvent event) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void buttonImportAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        // Adding import text from .txt files
        // At the moment it adds text to listView later need to change it
        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()))) {

                File file = new File("wordList");
                FileWriter fr = new FileWriter(file);

                String line = null;

                while ((line = reader.readLine()) != null) {
                    String words[] = line.split(" ");
                    for (String word : words) {
                        fr.write(word);
                        fr.write("\n");
                    }
                }
                fr.close();

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
        LeaderboardController.getUser(x);
        System.out.println(x);

        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }
    public void buttonWebscrape(ActionEvent event) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Webscrape.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void buttonStart(ActionEvent event) throws IOException{
        FXMLLoader leaderboards = new FXMLLoader(getClass().getResource("MainGame.fxml"));
        Scene scene = new Scene(leaderboards.load(), 600, 400);
        Stage stage = new Stage();
        GameController GameController = leaderboards.getController();
        GameController.getUser(x);

        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }

    public void getUser(String text) {
        x = text;
    }

}