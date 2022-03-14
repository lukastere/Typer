// previously from Pauls github called HelloController

package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.geometry.Bounds;
//import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;


public class GameController implements Initializable {
    private int wordcounter = 0;
    private int first = 1;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    //    @FXML
//    private AnchorPane rootPane;
//    @FXML
//    private Label mysLabel;
//    @FXML
//    private Label myDiff;
    @FXML
    private Text ProgWord;
    @FXML
    private Text fholeText;
    @FXML
    private Text secProgWord;
    @FXML
    private Text thrProgWord1;
    @FXML
    private TextField myTextField;
    //    @FXML
//    private ImageView correct;
//    @FXML
//    private ImageView incorrect;
    @FXML
    private Text seconds;
    @FXML
    private Label lable;
    @FXML
    private Label wordPerMin;
    @FXML
    private Label accuracy;
    //    @FXML
//    private Button myBButton;
    @FXML
    private Button StartBtn;
    @FXML
    private Button goBackToMain;
    @FXML
    private Button CorIncorWindow;
    @FXML private Label level;
    @FXML private Label username;

    static String x;
    ArrayList<String> words = new ArrayList<>();

    //9.19
    // add words to array list
    public void addToList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new
                    FileReader("wordList"));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(ActionEvent ae) throws IOException {
        //AnchorPane ae = FXMLLoader(HelloApplication.class.getResource("MainGame.fxml"));
    }
    public void setScore() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String user = x;
        String wpm = wordPerMin.getText();
        String acc = accuracy.getText();
        String time = seconds.getText();

        String insertFields = "INSERT INTO leaderboard (name, speed, accuracy, time) VALUES ('";
        String insertValues = user + "','" + wpm + "','" + acc + "','" + time +"')";
        String insertToLeaderboard = insertFields + insertValues;

        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(insertToLeaderboard);

        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//       if (dataCorIncor.text.equals("")){
//lable.setText("hi");
//       }else {
//lable.setText(dataCorIncor.text);
//       }


        //StartBtn.setVisible(false);
       // StartBtn.setDisable(true);
        goBackToMain.setVisible(false);
        goBackToMain.setDisable(true);
        addToList();
        //Collections.shuffle(words);

        ProgWord.setText(words.get(wordcounter));
        secProgWord.setText(words.get(wordcounter + 1));
        if (wordcounter > 1) {
            thrProgWord1.setText(words.get(wordcounter - 1));
        } else if (wordcounter == 0) {
            thrProgWord1.setText(words.get(wordcounter));

        }

        wordcounter++;
    }
    public void showLevel(String text){
        level.setText(text);
    }
    int timer = 60;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (myTextField.getText().equals("Game over")) {
                timer = 0;
                setScore();

            }
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                timer -= 1;
            }
//            else if (countAll +1 == words.size()) {
//                myTextField.setDisable(true);
//                myTextField.setText("Game over");
//                StartBtn.setVisible(true);
//                StartBtn.setDisable(false);
//                executor.shutdown();
//            }


            else {
                if (timer == -1) {
                    myTextField.setDisable(true);
                    myTextField.setText("Game over");
                 //   StartBtn.setVisible(true);
                   // StartBtn.setDisable(false);
                    goBackToMain.setVisible(true);
                    goBackToMain.setDisable(false);
                    setScore();

                    executor.shutdown();

//                    if (wordcounter -1 == words.size()) {

                    if (timer == -4) {
                      //  StartBtn.setVisible(true);
                       // StartBtn.setDisable(false);
                        goBackToMain.setVisible(true);
                        goBackToMain.setDisable(false);
                        executor.shutdown();
                    }

                    timer -= 1;
                }

            }
        }
    };



    ArrayList<String> Corr = new ArrayList<>();
    ArrayList<String> inCorr = new ArrayList<>();
//    ArrayList<String> Typed = new ArrayList<>();


    private int countAll = 0;
    private int counter = 0;


    public void StartGame(KeyEvent ke) {
        // only gets called once
        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }
//texts visamtext
        ArrayList<String> WT = new ArrayList<>();

        for (int d = 0; d < words.size(); d++) {
            String f = words.get(d);
            WT.add((String) f);
            //WT.add(String.valueOf(f.replaceAll(",", "")));


        }


        String listString = String.join(" ", WT);
        // listString.replaceAll(",", " ");

        fholeText.setText(listString);
        String last = words.get(words.size() - 1);

        if (ke.getCode().equals(KeyCode.ENTER)) {


            //String a = fholeText.getText();
            String s = myTextField.getText();
            String real = ProgWord.getText();
            countAll++;

            if (last.equals(ProgWord.getText())) {
                myTextField.setDisable(true);
                myTextField.setText("Game over");
               // StartBtn.setVisible(true);
               // StartBtn.setDisable(false);
                goBackToMain.setVisible(true);
                goBackToMain.setDisable(false);
                setScore();



            } else {            // if correct
                if (s.equals(real)) {


//                    myTextField.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    thrProgWord1.setFill(Color.GREEN);
                    counter++;
//                    fholeText.setFill(Color.GREEN);
                    wordPerMin.setText(String.valueOf(counter));
                    Corr.add(s);



                } else {
                    thrProgWord1.setFill(Color.RED);
//                    fholeText.setFill(Color.RED);
//                    myTextField.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    inCorr.add(s);
                    //Typed.add(s);

                }
                myTextField.setText("");
                accuracy.setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)));


                ProgWord.setText(words.get(wordcounter));
                if (last.equals(secProgWord.getText())) {
                    secProgWord.setText("");
                } else {
                    secProgWord.setText(words.get(wordcounter + 1));
                }
                if (wordcounter > 1) {
                    thrProgWord1.setText(words.get(wordcounter - 1));
                }
//                else if(wordcounter-1>= words.size()){
//                    thrProgWord1.setText(" " );
//                }
//                else if (countAll -1 >words.size()){
//                    myTextField.setDisable(true);
//                    myTextField.setText("Game over");
//                }
                else if (wordcounter == 0) {
                    thrProgWord1.setText(words.get(wordcounter));

                } else if (last.equals(thrProgWord1.getText())) {
                    thrProgWord1.setText("");
                }
                wordcounter++;
            }

        }

    }


    public void goBackToMain(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Log into Typer!");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void GoToCorIncorWindow(ActionEvent event){

        String CorrString = String.join(" ", Corr);
        String InCorrString = String.join(" ", inCorr);


        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("CorIncorWindow.fxml"));
            Parent root = (Parent) loader.load();

            CorIncorWindowController corIncorWindowController = loader.getController();
            corIncorWindowController.myFunction(CorrString);
            corIncorWindowController.mySecFunction(InCorrString);

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();


//            Parent root = FXMLLoader.load(getClass().getResource("CorIncorWindow.fxml"));
//            Stage stage = new Stage();
//
//            stage.setTitle("Log into Typer!");
//
//            stage.setScene(new Scene(root));
//            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void getUser(String text) {
        x = text;
    }
}



