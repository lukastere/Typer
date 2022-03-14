package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LeaderboardController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxLeaderboard;

    @FXML
    private TableView<LeaderboardData> leaderboards;
    @FXML
    private TableColumn<LeaderboardData, String> name;
    @FXML
    private TableColumn<LeaderboardData, Integer> speed;
    @FXML
    private TableColumn<LeaderboardData, Float> accuracy;
    @FXML
    private TableColumn<LeaderboardData, String> difficulty;
    @FXML
    private Label userLabel;
    ObservableList<String> tableList = FXCollections.observableArrayList("My Scores", "All High Scores");




    ObservableList<LeaderboardData> scores = FXCollections.observableArrayList();

    static String x;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxLeaderboard.setItems(tableList);
        name.setCellValueFactory(new PropertyValueFactory<LeaderboardData, String>("name"));
        speed.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Integer>("speed"));
        accuracy.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Float>("accuracy"));
        difficulty.setCellValueFactory(new PropertyValueFactory<LeaderboardData, String>("difficulty"));


        leaderboards.setItems(scores);




    }

    public void checkChoice(){
        scores.clear();
        try{
            if(comboBoxLeaderboard.getValue() == "My Scores"){
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectionDB = connectNow.getConnection();
                String getScores = "select * from newleaderboard where name = '" + x +"'";
                Statement statement = connectionDB.createStatement();
                ResultSet queryResult = statement.executeQuery(getScores);
                setUserScores();
                while (queryResult.next()) {
                    scores.add(new LeaderboardData(queryResult.getString("name"), queryResult.getInt("speed"), queryResult.getFloat("accuracy"), queryResult.getString("difficulty")));
                }
            }else if(comboBoxLeaderboard.getValue() == "All High Scores"){
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectionDB = connectNow.getConnection();
                String getScores = "SELECT * FROM newleaderboard";
                Statement statement = connectionDB.createStatement();
                ResultSet queryResult = statement.executeQuery(getScores);
                setUserScores();
                while (queryResult.next()) {
                    scores.add(new LeaderboardData(queryResult.getString("name"), queryResult.getInt("speed"), queryResult.getFloat("accuracy"), queryResult.getString("difficulty")));
                }
            }


        }catch (Exception e){
        }
    }
    public void getUser(String text) {
        x = text;
    }

    public void setUserScores(){
        if(comboBoxLeaderboard.getValue() == "My Score"){
            try{
                //connects to db
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectionDB = connectNow.getConnection();
                //sql query

                String getScores = "select * from newleaderboard where name = '" + x +"'";
                Statement statement = connectionDB.createStatement();
                ResultSet queryResult = statement.executeQuery(getScores);

                while (queryResult.next()) {
                    scores.add(new LeaderboardData(queryResult.getString("name"), queryResult.getInt("speed"), queryResult.getFloat("accuracy"), queryResult.getString("difficulty")));
                }

            }catch (Exception e){



            }

        }
    }

}
