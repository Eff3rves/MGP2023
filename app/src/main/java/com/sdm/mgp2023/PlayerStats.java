package com.sdm.mgp2023;

public class PlayerStats {
    public final static PlayerStats Instance = new PlayerStats();
    private int playerScore = 0;

    public void setPlayerScore(int score){
        playerScore = score;
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public void resetPlayerScore(){
        playerScore = 0;
    }

}
