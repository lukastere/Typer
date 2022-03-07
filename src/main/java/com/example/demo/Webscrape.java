package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Webscrape implements Initializable {

    @FXML private TextField webUrl;

    @FXML private ListView<String> scrapeText;

    @FXML private Button confirmScrape;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setConfirmScrape(ActionEvent event) {
        try{
            Document doc = Jsoup.connect(webUrl.getText()).get();
            Elements elements = doc.getElementsByTag("p");
            for (Element element : elements) {
                String items = element.text();
                scrapeText.getItems().add(items);
            }
        }catch (IOException ex){
            System.out.println("Scrape didn't work");
        }
    }
}
