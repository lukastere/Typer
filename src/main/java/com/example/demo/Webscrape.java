package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Webscrape implements Initializable {

    @FXML private TextField webUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
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
        }
    }
}