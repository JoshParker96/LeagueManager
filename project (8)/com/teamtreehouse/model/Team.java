package com.teamtreehouse.model;

import java.util.Set;
import java.util.TreeSet;

public class Team implements Comparable<Team>{
  public String mTeamName;
  public String mCoachName;
  public Set<Player> players;
  public static final int MAX_PLAYERS = 11;

  public Team(String teamName, String coachName) {
    mTeamName = teamName;
    mCoachName = coachName;
    players = new TreeSet<Player>();
  }
  
  @Override
  public int compareTo(Team other) {
    if(other.mTeamName.equals(mTeamName)) {
      return mCoachName.compareTo(other.mCoachName);
    }
    return mTeamName.compareTo(other.mTeamName);
  }
  
  public String getTeamName() {
    return mTeamName;
  }
  
  public String getCoachName() {
    return mCoachName;
  }
  
  public void addPlayer(Player player) {
   players.add(player);
  }
  
  public void removePlayer(Player player) {
    players.remove(player);
  }
  
  public int getMaxPlayers() {
    return MAX_PLAYERS;
  }
  
  public Set<Player> getTeamsPlayers() {
    return players;
  }
  
  public void addplayer(Player player) {
    players.add(player);
  }



}