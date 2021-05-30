package com.example.oopcwspringangular;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

//This class will be used to set and get the data of the matches both manually and randomly generated
@Component
public class Match implements Serializable {
    private FootballClub teamA;
    private FootballClub teamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;


    public FootballClub getTeamA() {
        return teamA;
    }

    public FootballClub getTeamB() {
        return teamB;
    }

    public int getTeamAScore(){
        return teamAScore;
    }

    public int getTeamBScore(){
        return teamBScore;
    }

    public Date getDate() {
        return date;
    }

    public void setTeamA(FootballClub teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(FootballClub teamB) {
        this.teamB = teamB;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

