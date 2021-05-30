package com.example.oopcwspringangular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

@SpringBootApplication
@RestController
public class OopcwspringangularApplication {
    @Autowired
    //creating a premiere League manager object
    static LeagueManager obj = PremierLeagueManager.getInstance();
    final static Scanner USER_INPUT = new Scanner(System.in);

    public static void main(String[]args) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
        //starting the springboot application before displaying the console menu to the user
        SpringApplication.run(OopcwspringangularApplication.class, args);
        displayMenu();
    }

    //Console application menu
    public  static  void displayMenu() throws IOException, URISyntaxException, ClassNotFoundException, ParseException {
        //loading the data automatically when the program starts
        obj.loadDataFromTextFile();

        while (true) {
            System.out.println("\n");
            System.out.println("*************** Displaying PremiereLeague Menu ***************");
            System.out.println("\n");
            System.out.println("Enter \"1\" to create new team to add to the tournament");
            System.out.println("Enter \"2\" to begin the game between two teams");
            System.out.println("Enter \"3\" to delete a team from the tournament");
            System.out.println("Enter \"4\" to view the statistics of each teams");
            System.out.println("Enter \"5\" to view the premiere league table");
            System.out.println("Enter \"6\" to save the played match details to a text file");
            System.out.println("Enter \"7\" to view the GUI application");
            System.out.println("Enter \"q\" to exit the program");
            System.out.println("\n");
            System.out.print("Select an option from the above menu: ");
            String select = USER_INPUT.nextLine().toLowerCase();

            switch(select) {
                case "1":
                    addTeam();
                    break;
                case "2":
                    beginGame();
                    break;
                case "3":
                    deleteTeam();
                    break;
                case "4":
                    statisticsOfTeam();
                    break;
                case "5":
                    obj.scoreTableOfLeague();
                    break;
                case "6":
                    obj.saveDataToTextFile();
                    break;
                case "7":
                    obj.guiApplication();
                    break;
                case "q":
                    System.exit(1);
                default:
                    System.out.println("Wrong Command");
            }
        }
    }

    //Getting inputs to add a team
    public static void addTeam(){
        FootballClub club = new FootballClub();

        System.out.println("\n");
        System.out.print("Insert Club Name: ");
        String line = USER_INPUT.nextLine().toUpperCase();
        assert false;
        club.setName(line);


        System.out.print("Insert Club Location: ");
        String entry = USER_INPUT.nextLine().toUpperCase();
        club.setLocation(entry);

        obj.addTeam(club,line,entry);
    }

    //getting inputs to delete a team
    public static void deleteTeam(){
        System.out.println("\n");
        System.out.println("*************** Enter the Premiere League club name to delete it from the league ***************");
        System.out.println("\n");
        System.out.print("Insert club name: ");
        String line = USER_INPUT.nextLine().toUpperCase();

        obj.deleteTeam(line);
    }

    //getting inputs to get a club name to view the statistics
    public static void statisticsOfTeam(){
        System.out.println("\n");
        System.out.print("Insert Football club name :");
        String decision = USER_INPUT.nextLine().toUpperCase();

        obj.statisticsOfTeam(decision);
    }

    //Getting inputs to add a played match
    public static void beginGame() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.print("Enter the date in the (format of (MM-dd-yyyy):");
        String input = scanner.nextLine().trim();

        System.out.print("Enter the first team: ");
        String line = scanner.nextLine().toUpperCase();

        System.out.print("Enter the Second team: ");
        String entry = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Enter the Team A goals: ");
            int score = scanner.nextInt();

            System.out.print("Enter the Team B goals: ");
            int insert = scanner.nextInt();

            obj.beginGame(input, line, entry, score, insert);

        }catch (InputMismatchException ee){
            System.out.println("\n");
            System.out.println("Integer value required");
        }
    }
}
