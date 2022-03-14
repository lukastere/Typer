package com.example.demo;
//Previously from lukas git called MainController
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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


    @FXML
    public ComboBox<String> myComboBox;
@FXML
public Label username;
    @FXML public Button startButton;

    ObservableList<String> list = FXCollections.observableArrayList("Import/Scrape","Easy", "Medium", "Hard");


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
        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }
    public void buttonWebscrape() throws IOException{
        FXMLLoader webscrape = new FXMLLoader(getClass().getResource("Webscrape.fxml"));
        Scene scene = new Scene(webscrape.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }
    public void myTfunct(String text){
        username.setText(text);
    }

    public void buttonStart(ActionEvent event) throws IOException, NullPointerException{
        if(myComboBox.getValue().equals("Easy") ){
            try{
                File file = new File("wordList");
                FileWriter fr = new FileWriter(file);
                BufferedReader reader = new BufferedReader(new FileReader("easy"));
                String line = null;
                while ((line = reader.readLine()) != null){
                    String[] words = line.split(" ");
                    for (String word : words) {
                        fr.write(word);
                        fr.write("\n");
                    }
                }
                fr.close();

            }catch (IOException ex){
                System.out.println("Couldn't read easy text contents");
            }
        }
        else if(myComboBox.getValue().equals("Medium")){
            try{
                File file = new File("wordList");
                FileWriter fr = new FileWriter(file);
                BufferedReader reader = new BufferedReader(new FileReader("medium"));
                String line = null;
                while ((line = reader.readLine()) != null){
                    String[] words = line.split(" ");
                    for (String word : words) {
                        fr.write(word);
                        fr.write("\n");
                    }
                }
                fr.close();

            }catch (IOException ex){
                System.out.println("Couldn't read medium text contents");
            }
        }
        else if(myComboBox.getValue().equals("Hard")){
            try{
                File file = new File("wordList");
                FileWriter fr = new FileWriter(file);
                BufferedReader reader = new BufferedReader(new FileReader("hard"));
                String line = null;
                while ((line = reader.readLine()) != null){
                    String[] words = line.split(" ");
                    for (String word : words) {
                        fr.write(word);
                        fr.write("\n");
                    }
                }
                fr.close();

            }catch (IOException ex){
                System.out.println("Couldn't read hard text contents");
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        GameController gameController = loader.getController();
        gameController.showLevel(myComboBox.getValue());
        gameController.tmyTfunct(username.getText());
        Stage stage = new Stage();
        stage.setTitle("FastFingerTips");
        stage.setScene(scene);
        stage.show();
    }

}