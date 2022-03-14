package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Webscrape {

    @FXML private TextField webUrl;
    private Stage stage;
    private Scene scene;

    public void setConfirmScrape(ActionEvent event) {
        try{
            Document doc = Jsoup.connect(webUrl.getText()).get();
            Elements elements = doc.getElementsByTag("p");
            File file = new File("wordList");
            FileWriter fr = new FileWriter(file);
            String items = elements.text();
            String[] words = items.split(" ");
            for (String word : words) {
                fr.write(word);
                fr.write("\n");
            }
            fr.close();
        }catch (IOException ex){
            System.out.println("Scrape didn't work");
        } catch (IllegalArgumentException ex){
            System.out.println("Didn't insert full link example: (https://delfi.lt)");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Didn't insert full link example: (https://delfi.lt)");
            alert.showAndWait();
        }
    }
    public void back(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}