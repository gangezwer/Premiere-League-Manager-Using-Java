package com.example.oopcwspringangular;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface LeagueManager {
    void saveDataToTextFile() throws IOException;
    void deleteTeam(String line);
    void scoreTableOfLeague();
    void statisticsOfTeam(String decision);
    void beginGame(String input,String line,String entry, int score, int insert) throws ParseException;
    void addTeam(FootballClub club,String line,String entry);
    void loadDataFromTextFile() throws IOException, ClassNotFoundException;
    void guiApplication() throws URISyntaxException, IOException;
}

