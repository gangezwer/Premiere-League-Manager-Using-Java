package com.example.oopcwspringangular;

import org.springframework.stereotype.Component;

//This class is inherited from the class SportsClub
@Component
public class FootballClub extends SportsClub {
    //These below declared attributes will be used to get the statistics of the football clubs
    private int winCount;
    private int drawCount;
    private int defeatCount;
    private int scoredGoalsCount;
    private int receivedGoalsCount;
    private int points;
    private int matchesPlayed;

    public int getWinCount(){
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getDefeatCount(){
        return defeatCount;
    }

    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }

    public int getReceivedGoalsCount() {
        return receivedGoalsCount;
    }

    public int getPoints() {
        return points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setWinCount(int winCount) {
        this.winCount= winCount;
    }

    public void setDrawCount(int drawCount){
        this.drawCount = drawCount;
    }

    public void setDefeatCount(int defeatCount) {
        this.defeatCount = defeatCount;
    }

    public void setScoredGoalsCount(int scoredGoalsCount){
        this.scoredGoalsCount = scoredGoalsCount;
    }

    public void setRecievedGoalsCount(int receivedGoalsCount){
        this.receivedGoalsCount = receivedGoalsCount;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void setMatchesPlayed(int matchesPlayed){
        this.matchesPlayed = matchesPlayed;
    }

}