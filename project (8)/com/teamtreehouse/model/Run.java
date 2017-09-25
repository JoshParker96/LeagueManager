package com.teamtreehouse.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Run {
  public Team team;
  public String teamName;
  public String coachName;
  public String teamChoice;
  public Set<Team> teams;
  public Scanner scanner;
  public Player player;
  public Player[] listOfPlayers;
  public List<Player> playerList;
  public Set<Player> playerSet;
  public Set<Player> teamedPlayers;
  
  public Run() {
    team = new Team(teamName, coachName);
    teams = new TreeSet<Team>();
    listOfPlayers = Players.load();
    playerList = Arrays.asList(listOfPlayers);
    playerSet = new TreeSet<Player>(playerList);
    teamedPlayers = new TreeSet<Player>();
    scanner = new Scanner(System.in);
  }

  public void displayMenu() {
    System.out.print("\n");
    System.out.print("=========MENU=========\n");
    System.out.print("create - create a new team\n");
    System.out.print("add - add a player to a team\n");
    System.out.print("remove - remove a player from a team\n");
    System.out.print("print - print chosen teams players\n");
    System.out.print("report - view a report of a team by height\n");
    System.out.print("balance - view a report of a team by experience\n");
    System.out.println("exit - exit the program\n");
  }
  
  public String promptForOption() throws IOException {
    System.out.printf("Select an option: ");
    String answer = scanner.nextLine();
    return answer;
  }
  
  public Team promptForNewTeam() throws IOException {
    do {
      System.out.print("\n");
      System.out.print("What is your team name? ");
      teamName = scanner.nextLine();
    } while(teamName.isEmpty());
    do {
      System.out.print("What is your coach name? ");
      coachName = scanner.nextLine();
    } while (coachName.isEmpty());
    return new Team(teamName, coachName);
  }
  
  public String promptForTeam() throws IOException {
    System.out.print("\n");
    System.out.print("***AVAILABLE TEAMS***\n");
    for (Team team : teams) {
      System.out.println(team.getTeamName() + "\n");
    }
    System.out.print("select a team: ");
    String choice = scanner.nextLine();
    System.out.print("\n");
    return choice;
  }
  
  public String promptTeamToAddPlayer(Player mPlayer) throws IOException {
    System.out.print("\n");
    System.out.print("***AVAILABLE TEAMS***\n");
    for (Team team : teams) {
      System.out.println(team.getTeamName() + "\n");
    }
    System.out.printf("select a team to add %s: ", mPlayer);
    String choice = scanner.nextLine();
    System.out.print("\n");
    return choice;
  }
  
  public String promptTeamToRemovePlayer(Player mPlayer) throws IOException {
    System.out.print("\n");
    System.out.print("***AVAILABLE TEAMS***\n");
    for (Team team : teams) {
      System.out.println(team.getTeamName() + "\n");
    }
    System.out.printf("select a team to remove %s: ", mPlayer);
    String choice = scanner.nextLine();
    System.out.print("\n");
    return choice;
  }
  
  public Player promptForAvailablePlayers(String teamChoice) throws IOException {
    List<String> roster = new ArrayList<String>();
    List<Player> mPlayers = new ArrayList<Player>(playerSet);
    System.out.printf("***%s available players to choose from***\n", teamChoice);
    System.out.println(" ");
    for(Player player : mPlayers) {
      roster.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
    }  
    int index = promptForPosistion(roster);
    return mPlayers.get(index);
  }
  
  public Player promptAllAvailablePlayers() throws IOException {
    List<String> roster = new ArrayList<String>();
    List<Player> mPlayers = new ArrayList<Player>(playerSet);
    System.out.printf("***available players to choose from***\n");
    System.out.println(" ");
    for(Player player : mPlayers) {
      roster.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
    }  
    int index = promptForPosistion(roster);
    return mPlayers.get(index);
  }
  
  public Player promptTeamedPlayers() throws IOException {
    List<String> roster = new ArrayList<String>();
    List<Player> mPlayers = new ArrayList<Player>(teamedPlayers);
    System.out.printf("***available players to choose from***\n");
    System.out.println(" ");
    for(Player player : mPlayers) {
      roster.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
    }  
    int index = promptForPosistion(roster);
    return mPlayers.get(index);
  }
  
  public int promptForPosistion(List<String> posistion) throws IOException {
    int choice = 1;
    try {
      for (String player : posistion) {
        System.out.printf("%d.)%s\n", choice, player);
        choice++;
      }
      System.out.print("\n");
      System.out.print("Select an option: ");
      String choiceAsString = scanner.nextLine();
      choice = Integer.parseInt(choiceAsString.trim());
    } catch(IllegalArgumentException iae) {
      System.out.print("problem with index" + iae.getMessage());
    }
    return choice -1;
  }
  
  public Player promptTeamsPlayers(String teamChoice, Set<Player> mPlayers) throws IOException {
    List<String> roster = new ArrayList<>();
    List<Player> players = new ArrayList<>(mPlayers);
    for(Player player : players) {
      roster.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
    }
    int posistion = promptForPosistion(roster);
    return players.get(posistion);
  }
  
  private Map<String, Set<String>> byHeight(Set<Player> roster) throws IOException{
   Map<String, Set<String>> heightMap = new TreeMap<>();
     Set<String> groupA = new TreeSet<>();
     Set<String> groupB = new TreeSet<>();
     Set<String> groupC = new TreeSet<>();
     String params1 = "Height range between 35 and 40 in.";
     String params2 = "Height range between 41 and 46 in.";
     String params3 = "Height range between 47 and 50 in.";
    
     for (Player player : roster) {
       if(player.getHeightInInches() >= 35 && player.getHeightInInches() <= 40) {
         groupA.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
       } else if (player.getHeightInInches() >= 41 && player.getHeightInInches() <= 46) {
         groupB.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
       } else if (player.getHeightInInches() >= 47 && player.getHeightInInches() <= 50) {
         groupC.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
       } else {
         System.out.println("An error occurred printing the roster by height."); 
       }
     }
    
     heightMap.put(params1, groupA);
     heightMap.put(params2, groupB);
     heightMap.put(params3, groupC);

    for (Map.Entry<String, Set<String>> entry : heightMap.entrySet()) {
      System.out.println("\n" + entry.getKey() + "\n");
      List<String> group = new ArrayList<>(entry.getValue());
         for (String player : group) {
          
          System.out.println(player);
         }
    }
    return heightMap;
  }
  
   public Set<String> groupByHeight(Set<Player> roster) throws IOException {
    return byHeight(roster).keySet();
  }
  
  private Player promptRoster(String teamChoice, Set<Player> roster) throws IOException {
    List<String> mRoster = new ArrayList<>();
    List<Player> players = new ArrayList<>(roster);
    for (Player player : players) {
      mRoster.add(player.getLastName() + " " + player.getFirstName() + " (Height: " +
                 player.getHeightInInches() + " inches - Experienced " +
                 player.isPreviousExperience() + ")");
      
   }
    System.out.printf("Players for the %s: %n", teamChoice);
    int index = promptForPosistion(mRoster);
    return players.get(index);
  }   
  
  
  public Map<String, String> byExperience() throws IOException{
    Map<String, String> experienceMap = new TreeMap<>();
    for (Team team : teams) {
      String mTeam = team.getTeamName();
      int group1 = 0;
      int group2 = 0;
      String inex = "Number of INEXPERIENCED players";
      String exper = "Number of EXPERIENCED players";
      Set<Player> mPlayers = team.getTeamsPlayers();
      for (Player player : mPlayers) {
        boolean check = player.isPreviousExperience();
        if(!check) {
          group1++;
        } else {
          group2++;
        }
      }
      System.out.print("\n");
      System.out.printf("***Team %s player stats***\n", mTeam);
      System.out.println("EXPERIENCED PLAYERS: " + Integer.toString(group2));
      System.out.println("INEXPERIENCED PLAYERS: " + Integer.toString(group1));
    }
    return experienceMap;
  }
  
  public Set<String> groupByExperienced() throws IOException{
    return byExperience().keySet();
  }
  
  public void printTeamsPlayers (Set<Player> roster) throws IOException{
    int counter = 1;
    List<Player> mRoster = new ArrayList<>();
    List<Player> players = new ArrayList<>(roster);
    for (Player player : players) {
      System.out.printf("%d) %s %s (Height: %s inches - Experienced %s\n",
                       counter,
                       player.getLastName(),
                       player.getFirstName(),
                       player.getHeightInInches(),
                       player.isPreviousExperience());
        counter++;
    }
  }
  
  public void run() {
    String option = "";
    Set<Player> mListOfPlayers = new TreeSet<Player>();
    do {
      displayMenu();
      try {
        option = promptForOption();
        
        switch (option) {
          case "create":
          team = promptForNewTeam();
          teams.add(team);
         break;
          
          case "add":
          if (teams.isEmpty()) {
            System.out.print("There are NO teams available, please create a team first\n");
          } else {
            player = promptAllAvailablePlayers();
             for (Team team : teams) {
               String teamChoice = promptTeamToAddPlayer(player);
               if (team.getTeamName().equals(teamChoice)) {
                 if (team.getTeamsPlayers().size() >= team.getMaxPlayers()) {
                   System.out.printf("Team %s has reached the maximum nmber of players.", teamChoice);
                 } else {
                     team.addPlayer(player);
                     teamedPlayers.add(player);
                     mListOfPlayers.add(player);
                     playerSet.remove(player);
                     System.out.printf("You added %s to team %s, coached by %s and now has %d players\n",
                                      player, teamChoice, team.getCoachName(), team.getTeamsPlayers().size());
                     System.out.print("\n");
                 }
               }
             }
          }
         break;
          case "remove":
            if(teams.isEmpty() || teamedPlayers.isEmpty()) {
              System.out.println("Action is not available");
            } else {
              player = promptTeamedPlayers();
             for (Team team : teams) {
               String teamChoice = promptTeamToRemovePlayer(player);
               if (team.getTeamName().equals(teamChoice)) {
                 if (team.getTeamsPlayers().size() <= 0) {
                   System.out.printf("%s istn' in team %s",player, teamChoice);
                 } else {
                   if (playerSet.isEmpty()) {
                     System.out.println("All teams are full");
                   } else {
                     team.removePlayer(player);
                     teamedPlayers.remove(player);
                     mListOfPlayers.remove(player);
                     playerSet.add(player);
                     System.out.printf("You removed %s from team %s, coached by %s and now has %d players\n",
                                      player, teamChoice, team.getCoachName(), team.getTeamsPlayers().size());
                     System.out.print("\n");
                   }
                 }
               }
             }
          }
         break;
          case "report":
          if (teams.isEmpty()) {
            System.out.println("There are no created teams. Create a team first ");
          } else {
            teamChoice = promptForTeam();
            for(Team team : teams) {
              if(team.getTeamName().equals(teamChoice)) {
                if (team.getTeamsPlayers().size() <= 0) {
                  System.out.println("There are no players added to any teams");
                } else {
                  mListOfPlayers = team.getTeamsPlayers();
                  groupByHeight(mListOfPlayers);
                }
              }
            }
          }
         break;
          case "balance":
          if (teams.isEmpty()) {
            System.out.println("There are no created teams. Create a team first");
          } else {
            teamChoice = promptForTeam();
            for (Team team : teams) {
              if (team.getTeamName().equals(teamChoice)) {
                if (team.getTeamsPlayers().size() <= 0) {
                  System.out.println("There are no players added to any teams");
                } else {
                  groupByExperienced();
                }
              }
            }
          }
         break;
          case "print":
          if (teams.isEmpty()) {
            System.out.println("There are no created teams");
          } else {
            for (Team team : teams) {
              teamChoice = promptForTeam();
              if (team.getTeamName().equals(teamChoice)) {
                mListOfPlayers = team.getTeamsPlayers();
                System.out.printf("Available players for team %s\n", teamChoice);
                System.out.print("\n");
                if (mListOfPlayers.size() <= 0) {
                  System.out.printf("There are no players for team %s\n", teamChoice);
                  System.out.print("\n");
                } else {
                  printTeamsPlayers(mListOfPlayers);
                }
              }
            }
          }
         break;
          case "exit":
          System.out.print("Good luck!!!\n");
         break;
          default:
          System.out.println("Please select a valid option from the menu");
         break;
        }
      }catch(IOException ioe) {
        System.out.println("Problem with input");
        ioe.printStackTrace();
      }
    }while(!option.equals("exit"));
  }
}
