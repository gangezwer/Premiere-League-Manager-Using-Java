package com.example.oopcwspringangular;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.oopcwspringangular.PremierLeagueManager.generalLeague;

//This class will be the console application
@RestController
public class OopcwspringangularApplicationController {
    //creating an object for random match
    private static Random randomGenerator = new Random();
    public static ArrayList<Match> randomMatch = new ArrayList<>();


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping( "/pointComparator")
    public List<ArrayList<FootballClub>> pointComparator() throws IOException, ClassNotFoundException {
        PremierLeagueManager.generalLeague.sort(new PointComparator());
        PremierLeagueManager.deletedClubs.sort(new PointComparator());
        return Stream.of(PremierLeagueManager.generalLeague,PremierLeagueManager.deletedClubs).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/winComparator")
    public List<ArrayList<FootballClub>> winComparator() throws IOException, ClassNotFoundException {
        PremierLeagueManager.generalLeague.sort(new WinComparator());
        PremierLeagueManager.deletedClubs.sort(new WinComparator());

        return Stream.of(PremierLeagueManager.generalLeague,PremierLeagueManager.deletedClubs).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/goalComparator")
    public List<ArrayList<FootballClub>> goalComparator() throws IOException, ClassNotFoundException {
        PremierLeagueManager.generalLeague.sort(new GoalComparetor());
        PremierLeagueManager.deletedClubs.sort(new GoalComparetor());

        return Stream.of(PremierLeagueManager.generalLeague,PremierLeagueManager.deletedClubs).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/dateComparator")
    public List<ArrayList<Match>> dateComparator() throws IOException, ClassNotFoundException, ParseException {

        PremierLeagueManager.matches.sort(new MatchDateComparetor());
        return Stream.of(PremierLeagueManager.matches).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/randomMatch")
    public List<ArrayList<Match>> randomMatch() throws ParseException, IOException, ClassNotFoundException {
        //if the arraylist size is not bigger than two it wo't auto generate the match
        if (generalLeague.size() >= 2){
            //Auro generating the date
            int day = ThreadLocalRandom.current().nextInt(1, 32);
            int month = ThreadLocalRandom.current().nextInt(1,13);
            int year = ThreadLocalRandom.current().nextInt(2020,2040);

            String input = (day+"-"+month+"-"+year);

            if (!input.matches("^(((((((0?[13578])|(1[02]))[\\.\\-/]?((0?[1-9])|([12]\\d)|(3[01])))|(((0?[469])|(11))[\\.\\-/]?((0?[1-9])|([12]\\d)|(30)))|((0?2)[\\.\\-/]?((0?[1-9])|(1\\d)|(2[0-8]))))[\\.\\-/]?(((19)|(20))?([\\d][\\d]))))|((0?2)[\\.\\-/]?(29)[\\.\\-/]?(((19)|(20))?(([02468][048])|([13579][26])))))$")){
                return null;
            }

            Date date;
            date = new SimpleDateFormat("MM-dd-yyyy").parse(input);

            //Autogenarating the home and away team from the arraylist
            int index = randomGenerator.nextInt(generalLeague.size());
            FootballClub firstTeam = generalLeague.get(index);
            for (FootballClub clubs : generalLeague) {
                if (clubs.getName().equals(firstTeam)) {
                    firstTeam = clubs;
                }
            }

            int selection = randomGenerator.nextInt(generalLeague.size());
            FootballClub secondTeam = generalLeague.get(selection);
            for (FootballClub clubs : generalLeague) {
                if (clubs.getName().equals(secondTeam)) {
                    secondTeam = clubs;
                }
            }

            //auto generating the goals count for home and away team
            int firstTeamGoal = ThreadLocalRandom.current().nextInt(1, 14);

            int secondTeamGoal = ThreadLocalRandom.current().nextInt(1, 14);

            //System.out.println(date+" "+firstTeam.getName()+" "+secondTeam.getName()+" "+firstTeamGoal+" "+secondTeamGoal);

            Match match = new Match();//creating the match object

            match.setTeamA(firstTeam);
            match.setTeamB(secondTeam);
            match.setDate(date);
            match.setTeamAScore(firstTeamGoal);
            match.setTeamBScore(secondTeamGoal);

            for (Match mm: PremierLeagueManager.matches){//one team can play one match for a day
                if (mm.getTeamB().equals(secondTeam) && mm.getDate().equals(date) || mm.getTeamB().equals(firstTeam) && mm.getDate().equals(date)){
                    return null;
                }
            }

            for (Match mm: PremierLeagueManager.matches){
                if (mm.getTeamA().equals(firstTeam) && mm.getDate().equals(date) || mm.getTeamA().equals(secondTeam) && mm.getDate().equals(date)){
                    return null;
                }
            }

            for (Match mm: PremierLeagueManager.matches) {
                if (mm.getTeamA().equals(firstTeam) && mm.getTeamB().equals(secondTeam)) {//two teams can play only one match together in the tournament
                    return null;
                }
            }

            if (firstTeam.getName().equals(secondTeam.getName())){
                return null;
            }

            PremierLeagueManager.matches.add(match);
            //Storing the randomly generated match in a new arraylist
            randomMatch.add(match);

            firstTeam.setScoredGoalsCount(firstTeam.getScoredGoalsCount() + firstTeamGoal);
            firstTeam.setRecievedGoalsCount(firstTeam.getReceivedGoalsCount()+secondTeamGoal);
            firstTeam.setMatchesPlayed(firstTeam.getMatchesPlayed()+1);
            secondTeam.setScoredGoalsCount(secondTeam.getScoredGoalsCount() + secondTeamGoal);
            secondTeam.setRecievedGoalsCount(secondTeam.getReceivedGoalsCount()+firstTeamGoal);
            secondTeam.setMatchesPlayed(secondTeam.getMatchesPlayed()+1);

            if (firstTeamGoal > secondTeamGoal) {
                firstTeam.setPoints(firstTeam.getPoints()+3);
                firstTeam.setWinCount(firstTeam.getWinCount()+1);
                secondTeam.setDefeatCount(secondTeam.getDefeatCount()+1);
            }

            else if (firstTeamGoal < secondTeamGoal) {
                secondTeam.setPoints(secondTeam.getPoints()+3);
                secondTeam.setWinCount(secondTeam.getWinCount()+1);
                firstTeam.setDefeatCount(firstTeam.getDefeatCount()+1);
            }

            else {
                firstTeam.setPoints(firstTeam.getPoints()+1);
                secondTeam.setPoints(secondTeam.getPoints()+1);
                firstTeam.setDrawCount(firstTeam.getDrawCount()+1);
                secondTeam.setDrawCount(secondTeam.getDrawCount()+1);
            }
        }
        return Stream.of(randomMatch).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/matchFinder")
    public List<ArrayList<Match>> matchFinder() throws ParseException, IOException, ClassNotFoundException {
        return Stream.of(PremierLeagueManager.matches).collect(Collectors.toList());
    }
}
