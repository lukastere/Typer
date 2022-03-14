// constructor for leaderboard score object
package com.example.demo;

public class LeaderboardData {

    private String name;
    private int speed;
    private float accuracy;
    private float time;

    public LeaderboardData(String name, int speed, float accuracy, float time) {
        this.name = name;
        this.speed = speed;
        this.accuracy = accuracy;
        this.time = time;
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

    public float getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "LeaderboardData{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", accuracy=" + accuracy +
                ", time=" + time +
                '}';
    }
}