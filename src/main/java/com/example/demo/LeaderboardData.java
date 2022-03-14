// constructor for leaderboard score object
package com.example.demo;

public class LeaderboardData {

    private String name;
    private int speed;
    private float accuracy;
    private String difficulty;

    public LeaderboardData(String name, int speed, float accuracy, String difficulty) {
        this.name = name;
        this.speed = speed;
        this.accuracy = accuracy;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "LeaderboardData{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", accuracy=" + accuracy +
                ", difficulty=" + difficulty +
                '}';
    }
}