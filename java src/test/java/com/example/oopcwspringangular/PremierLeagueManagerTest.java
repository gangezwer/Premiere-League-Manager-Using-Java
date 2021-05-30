package com.example.oopcwspringangular;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


class PremierLeagueManagerTest {
    private static ArrayList<FootballClub> clubs = new ArrayList<>();
    private static ArrayList<Match> matches = new ArrayList<>();
    private static ArrayList<FootballClub> deletedClubs = new ArrayList<>();

    @Test
    void addTeam() {
        FootballClub club = new FootballClub();
        club.setName("game");
        club.setLocation("vavuniya");

        clubs.add(club);
    }

    @Test
    void beginGame() throws ParseException {
        FootballClub club =new FootballClub();
        FootballClub club1 = new FootballClub();
        club.setName("game");
        club1.setName("wizard");
        String input = "10-20-2000";
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(input);
        Match match = new Match();
        match.setTeamA(club);
        match.setTeamB(club1);
        match.setTeamAScore(2);
        match.setTeamBScore(7);
        match.setDate(date);

        matches.add(match);
    }

    @Test
    void statisticsOfTeam() {
        FootballClub club = new FootballClub();
        club.setName("gangez");
        club.setPoints(2);
        club.setDrawCount(3);
        club.setWinCount(3);
        club.setDefeatCount(2);
        club.setRecievedGoalsCount(5);
        club.setScoredGoalsCount(4);
        club.setMatchesPlayed(7);

        club.getName().equals("gangez");
        club.getPoints();
        club.getDrawCount();
        club.getWinCount();
        club.getDefeatCount();
        club.getReceivedGoalsCount();
        club.getScoredGoalsCount();
        club.getMatchesPlayed();
    }

    @Test
    void scoreTableOfLeague() {
        clubs.sort(new PointComparator());
        deletedClubs.sort(new PointComparator());
        for (FootballClub club: clubs){
            System.out.println(club.getPoints() +"\t\t\t"+club.getWinCount() +"\t\t\t"+club.getDefeatCount()+"\t\t\t"+club.getDrawCount()+"\t\t\t\t"+club.getMatchesPlayed()+"\t\t\t\t"+club.getScoredGoalsCount()+"\t\t\t\t"+club.getReceivedGoalsCount()+"\t\t\t\t"+(club.getScoredGoalsCount() -  club.getReceivedGoalsCount())+"\t\t\t\t\t"+club.getName());
        }

        for (FootballClub club: deletedClubs){
            System.out.println(club.getPoints() +"\t\t\t"+club.getWinCount() +"\t\t\t"+club.getDefeatCount()+"\t\t\t"+club.getDrawCount()+"\t\t\t\t"+club.getMatchesPlayed()+"\t\t\t\t"+club.getScoredGoalsCount()+"\t\t\t\t"+club.getReceivedGoalsCount()+"\t\t\t\t"+(club.getScoredGoalsCount() -  club.getReceivedGoalsCount())+"\t\t\t\t\t"+club.getName());
        }
    }

    @Test
    void deleteTeam() {
        FootballClub club = new FootballClub();
        club.setName("game");

        clubs.remove(club.getName().equals("game"));
        deletedClubs.add(club);
    }

}