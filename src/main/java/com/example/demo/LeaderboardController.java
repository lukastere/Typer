package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    private TableColumn<LeaderboardData, Float> time;

    ObservableList<String> tableList = FXCollections.observableArrayList("My Scores", "Latest High Scores");



    ObservableList<LeaderboardData> scores = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try{
            //connects to db
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectionDB = connectNow.getConnection();
            //sql query
            String getScores = "SELECT * FROM leaderboard";
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getScores);

            while (queryResult.next()) {
                scores.add(new LeaderboardData(queryResult.getString("name"), queryResult.getInt("speed"), queryResult.getFloat("accuracy"), queryResult.getFloat("time")));
            }

        }catch (Exception e){



        }



        comboBoxLeaderboard.setItems(tableList);
        name.setCellValueFactory(new PropertyValueFactory<LeaderboardData, String>("name"));
        speed.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Integer>("speed"));
        accuracy.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Float>("accuracy"));
        time.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Float>("time"));


        leaderboards.setItems(scores);




    }
}