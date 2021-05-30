package com.example.oopcwspringangular;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

@Component
class PremierLeagueManager implements LeagueManager{

    //creating an interface object to get the instance
    private static LeagueManager obj = null;
    //setting the maximum club count that can stored
    private static final int NUMBER_OF_CLUBS=20;
    //creating a arraylist to store the clubs and it's details
    public static ArrayList<FootballClub> generalLeague = new ArrayList<>();
    //creating a arraylist to store the matches and it's details
    public static ArrayList<Match> matches = new ArrayList<>();
    //creating a arraylist to store the relegated clubs and it's details
    public static ArrayList<FootballClub>deletedClubs = new ArrayList<>();


    //this provider class is used to return a signature object which implements a specified algorithm
    //instance class variables belong to an instance of this class
    public static LeagueManager getInstance(){
        if (obj == null){
            synchronized (PremierLeagueManager.class){
                if (obj == null){
                    obj = new PremierLeagueManager();
                }
            }
        }
        return obj;
    }

    @Override
    public void addTeam(FootballClub club,String line,String entry){
        //If the arraylist reaches it's maximum which is NUMBER_OF_CLUBS, this error will be thrown
        if(generalLeague.size() == NUMBER_OF_CLUBS) {
            System.out.println("\n");
            System.out.println("Can't add more clubs to the Premiere league");
            return;
        }

        //setting the name for the club tot the football club object
        club.setName(line);

        if (generalLeague.contains(club)){//validating club name and location
            System.out.println("\n");
            System.out.println("This club was already registered for the Premiere League");
            return;
        }

        for(FootballClub cll : generalLeague) {//validating club name only
            if(cll.getName().equals(line)){
                System.out.println("\n");
                System.out.println("This club name was already registered for the Premiere League choose different name");
                return;
            }
        }

        //setting the location for the club tot the football club object
        club.setLocation(entry);

        //have to make the validation to check whether it's in the deleted clubs arraylist or not
        for (FootballClub rr: deletedClubs){
            if (rr.getName().equals(line)){
                System.out.println("\n");
                System.out.println("This named club got relegated in the Premier League already");
                return;
            }
        }

        //If the input the club name and location is empty this error will be thrown
        if(club.getName().isEmpty() ){
            System.out.println("\n");
            System.out.println("Please enter a name for the club to register it to the Premiere League");
            return;
        }
        if (club.getLocation().isEmpty()){
            System.out.println("\n");
            System.out.println("Please enter a location for the club to register it to the Premiere League");
            return;
        }
        //adding the club object to the arraylist which contains name and location of the club
        generalLeague.add(club);
    }

    @Override
    public void beginGame(String input,String line,String entry, int score, int insert) throws ParseException {
        //if the clubs array list size is lesser than 2 show this error
        if(generalLeague.size() == 0 || generalLeague.size() == 1) {
            System.out.println("\n");
            System.out.println("Add more teams to Start the Premiere League");
        }
        //The input format should be month - date -year
        //This regex validates date between 1900-2099 years and also handles leap year
        //https://regexlib.com/Search.aspx?k=MM-dd-yyyy&c=-1&m=-1&ps=20
        if (!input.matches("^(((((((0?[13578])|(1[02]))[\\.\\-/]?((0?[1-9])|([12]\\d)|(3[01])))|(((0?[469])|(11))[\\.\\-/]?((0?[1-9])|([12]\\d)|(30)))|((0?2)[\\.\\-/]?((0?[1-9])|(1\\d)|(2[0-8]))))[\\.\\-/]?(((19)|(20))?([\\d][\\d]))))|((0?2)[\\.\\-/]?(29)[\\.\\-/]?(((19)|(20))?(([02468][048])|([13579][26])))))$")){
            System.out.println("\n");
            System.out.println("The date format you entered is wrong");
            return;
        }

        //Changing the string date input to the java Date using SimpleDateFormat
        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            //changing the timezone
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = simpleDateFormat.parse(input);
        }catch (ParseException ee){
            System.out.println("\n");
            System.out.println("Date format you entered is wrong");
            return;
        }

        //Storing a input football club to the variable
        FootballClub firstTeam = null;
        for (FootballClub clubs: generalLeague){
            if (clubs.getName().equals(line)){
                firstTeam = clubs;
            }
        }

        //If there's no team registered but you try to add it to play a match this error will shoe
        try {
            assert firstTeam != null;
            if ( !firstTeam.getName().equals(line)) {
                return;
            }
        }catch (NullPointerException xx){
            System.out.println("\n");
            System.out.println("There is no such Club registered in Premiere League");
            return;
        }

        FootballClub secondTeam = null;
        for (FootballClub clubs: generalLeague){
            if (clubs.getName().equals(entry)){
                secondTeam = clubs;
            }
        }

        //If there's no team registered but you try to add it to play a match this error will shoe
        try {
            assert secondTeam != null;
            if ( !secondTeam.getName().equals(entry)) {//if the second team is not null and the user entry for the second team is not found in arraylist return to menu
                return;
            }
        }catch (NullPointerException ss){
            System.out.println("\n");
            System.out.println("There is no such Club registered in Premiere League");
            return;
        }

        if (secondTeam.getName().equals(entry) == secondTeam.getName().equals(line)){ //same team cant play a match
            System.out.println("\n");
            System.out.println("You have to enter different registered team from Premiere League");
            return;
        }

        //storing the input goal count to the variable
        int firstTeamGoal = 0;
        //System.out.print("Enter the Team A goals: ");
        try {
            //int score = scanner.nextInt();//user need to enter an integer value for the goal count
            firstTeamGoal = score;
        }catch (InputMismatchException e){
            System.out.println("\n");
            System.out.println("Enter the integer value for the goal count");
        }

        if (firstTeamGoal <0){
            System.out.println("\n");
            System.out.println("The goal count can't be a negative number");
            return;
        }

        int secondTeamGoal = 0;
        //System.out.print("Enter the Team B goals: ");
        try {
            //int insert = scanner.nextInt();
            secondTeamGoal = insert;
        }catch (InputMismatchException e){
            System.out.println("\n");
            System.out.println("Enter integer value for the goal count");
        }

        if (secondTeamGoal <0){
            System.out.println("\n");
            System.out.println("The goal count can't be a negative number");
            return;
        }

        Match match = new Match();//creating the match object
        //storing the input values to object and creating a match object
        match.setTeamA(firstTeam);
        match.setTeamB(secondTeam);

        for (Match mm: matches){
            if (mm.getTeamA().equals(firstTeam) && mm.getDate().equals(date) || mm.getTeamA().equals(secondTeam) && mm.getDate().equals(date)){
                System.out.println("\n");
                System.out.println("One of the above team  already played the match for the day");
                return;
            }
        }

        for (Match mm: matches){//one team can play one match for a day
            if (mm.getTeamB().equals(secondTeam) && mm.getDate().equals(date) || mm.getTeamB().equals(firstTeam) && mm.getDate().equals(date)){
                System.out.println("\n");
                System.out.println("One of the above team  already played the match for the day");
                return;
            }
        }

        //i have to add validation for one match for both teams per day
        for (Match mm: matches) {
            if (mm.getTeamA().equals(firstTeam) && mm.getTeamB().equals(secondTeam)) {//two teams can play only one match together in the tournament
                System.out.println("\n");
                System.out.println("This match has been played already");
                return;
            }
        }

        match.setDate(date);
        match.setTeamAScore(firstTeamGoal);
        match.setTeamBScore(secondTeamGoal);
        //adding the created match object tot the arraylist
        matches.add(match);

        //setting the inputs to the specific attributes of match to the attributes of football class
        firstTeam.setScoredGoalsCount(firstTeam.getScoredGoalsCount() + firstTeamGoal);
        firstTeam.setRecievedGoalsCount(firstTeam.getReceivedGoalsCount()+secondTeamGoal);
        firstTeam.setMatchesPlayed(firstTeam.getMatchesPlayed()+1);
        secondTeam.setScoredGoalsCount(secondTeam.getScoredGoalsCount() + secondTeamGoal);
        secondTeam.setRecievedGoalsCount(secondTeam.getReceivedGoalsCount()+firstTeamGoal);
        secondTeam.setMatchesPlayed(secondTeam.getMatchesPlayed()+1);

        //setting the win,goal,defeat points to the respective teams
        //if a team wins it'll get 3 poits if loose nothing will gained
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
        //if the match is drawn each team will get one points
        else {
            firstTeam.setPoints(firstTeam.getPoints()+1);
            secondTeam.setPoints(secondTeam.getPoints()+1);
            firstTeam.setDrawCount(firstTeam.getDrawCount()+1);
            secondTeam.setDrawCount(secondTeam.getDrawCount()+1);
        }
    }

    @Override
    public void statisticsOfTeam(String decision) {
        //getting a club name as a input from user and displaying statistics
        for (FootballClub club : generalLeague) {
            if (club.getName().equals(decision)) {
                System.out.println("\n");
                System.out.println("*************** Football Club "+club.getName().toUpperCase()+" Statistics ***************");
                System.out.println("\n");
                System.out.println(" matches won:    " + club.getWinCount());
                System.out.println(" matches lost:   " + club.getDefeatCount());
                System.out.println(" matches draw:   " + club.getDrawCount());
                System.out.println(" scored goals:   " + club.getScoredGoalsCount());
                System.out.println(" received goals: " + club.getReceivedGoalsCount());
                System.out.println(" points:         " + club.getPoints());
                System.out.println(" matches played: " + club.getMatchesPlayed());
                return;
            }
        }
        //if the user input club is not found in arraylist this error will get thrown
        System.out.println("\n");
        System.out.println("There is no such club in Premiere League");
    }

    @Override
    public void scoreTableOfLeague() {
        System.out.println("\n");
        System.out.println("*************** Premiere League Table ***************");
        System.out.println("\n");
        //the clubs in the generalLeague arraylist will get sorted according to points descending order using comparator class
        generalLeague.sort(new PointComparator());
        deletedClubs.sort(new PointComparator());
        System.out.println("Points     Win         Loss        Draw       Matches Played    Scored Goals     Received Goals         Goal Difference               club");
        for (FootballClub club: generalLeague){
            System.out.println(club.getPoints() +"\t\t\t"+club.getWinCount() +"\t\t\t"+club.getDefeatCount()+"\t\t\t"+club.getDrawCount()+"\t\t\t\t"+club.getMatchesPlayed()+"\t\t\t\t"+club.getScoredGoalsCount()+"\t\t\t\t"+club.getReceivedGoalsCount()+"\t\t\t\t\t\t"+(club.getScoredGoalsCount() -  club.getReceivedGoalsCount())+"\t\t\t\t\t\t"+club.getName());
        }
        //displaying the relegated clubs at last in the score table
        deletedClubs.sort(new PointComparator());
        for (FootballClub club: deletedClubs){
            if (deletedClubs.contains(club) != generalLeague.contains(club))
                System.out.println(club.getPoints() +"\t\t\t"+club.getWinCount() +"\t\t\t"+club.getDefeatCount()+"\t\t\t"+club.getDrawCount()+"\t\t\t\t"+club.getMatchesPlayed()+"\t\t\t\t"+club.getScoredGoalsCount()+"\t\t\t\t"+club.getReceivedGoalsCount()+"\t\t\t\t\t\t"+(club.getScoredGoalsCount() -  club.getReceivedGoalsCount())+"\t\t\t\t\t\t"+club.getName());
        }
    }

    @Override
    public void loadDataFromTextFile() throws IOException {
        //loading the stored data from the files using object input stream
        try {
            FileInputStream fis = new FileInputStream("file.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            for (; ; ) {
                try {
                    FootballClub v = (FootballClub) ois.readObject();
                    generalLeague.add(v);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream("deleted_file.txt");
            ois = new ObjectInputStream(fis);

            for (; ; ) {
                try {
                    FootballClub v = (FootballClub) ois.readObject();
                    deletedClubs.add(v);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }

            fis = new FileInputStream("matches_file.txt");
            ois = new ObjectInputStream(fis);

            for (; ; ) {
                try {
                    Match v = (Match) ois.readObject();
                    matches.add(v);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }

            fis.close();
            ois.close();
            System.out.println("\n");
            System.out.println("Data has been Successfully Loaded from the File");

        }catch (EOFException ee){
            System.out.println("\n");
            System.out.println("There's no data in to load form the text file");
        }catch (FileNotFoundException ee){
            System.out.println("\n");
            System.out.println("There's no file to load the data");
        }catch (Exception e){
            System.out.println("\n");
            System.out.println("There's a error in loading the data from the file");
        }
    }

    @Override
    public void deleteTeam(String line) {
        //if the array list which contains clubs is empty this error will get thrown
        if(generalLeague.isEmpty()) {
            System.out.println("\n");
            System.out.println("Add teams to delete it from the Premiere League");
        }

        //if the user enterd club found in the generalLeague array list it'll get deleted from arraylist
        //the deleted club will get stored in the deletedClubs arraylist
        for (FootballClub club : generalLeague) {
            if (club.getName().equals(line)) {
                generalLeague.remove(club);
                deletedClubs.add(club);
                System.out.println("\n");
                System.out.println("Football Club " + club.getName() + " got removed");
                return;
            }
        }
        System.out.println("\n");
        System.out.println("No such club registered in the Premiere league");
    }

    @Override
    public void saveDataToTextFile() throws IOException {
        // storing the data in the file using the objects and object input stream
        try {
            //creating the objects
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream("file.txt");
                oos = new ObjectOutputStream(fos);

                for (FootballClub v : generalLeague) {
                    oos.writeObject(v);
                }

                oos.flush();

            } catch (FileNotFoundException ee) {
                System.out.println("file not found");
            }

            try {
                fos = new FileOutputStream("deleted_file.txt");
                oos = new ObjectOutputStream(fos);

                for (FootballClub d : deletedClubs) {
                    oos.writeObject(d);
                }

                oos.flush();

            } catch (FileNotFoundException ee) {
                System.out.println("file not found");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fos = new FileOutputStream("matches_file.txt");
                oos = new ObjectOutputStream(fos);

                for (Match d : matches) {
                    oos.writeObject(d);
                }

                oos.flush();
                fos.close();
                oos.close();

            } catch (FileNotFoundException ee) {
                System.out.println("file not found");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n");
            System.out.println("Data has been Successfully Saved to the File");

        }catch (FileNotFoundException ee){
            System.out.println("\n");
            System.out.println("Unable to save the data to the text file");
        }catch (Exception e){
            System.out.println("\n");
            System.out.println("Unable to save the data to the text file");
        }

    }

    @Override
    public void guiApplication() throws URISyntaxException, IOException, HeadlessException {
        //opeing the localhot of angular from the console
        //https://stackoverflow.com/questions/38754219/java-how-to-open-url-with-command-on-mac
        String Command="open "+"http://localhost:4200";
        Process Child=Runtime.getRuntime().exec(Command);

    }
}






