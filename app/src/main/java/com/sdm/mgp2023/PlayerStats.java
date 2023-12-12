package com.sdm.mgp2023;

public class PlayerStats {
    public final static PlayerStats Instance = new PlayerStats();
    private int playerScore = 0;
    private  int playerHp = 3;
    private float playerStartPosX = 100;
    private  float playerStartPosY = 400;
    public void setPlayerScore(int score){
        playerScore = score;
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public void resetPlayerScore(){
        playerScore = 0;
    }

    public void setPlayerHp(int Hp){
        playerHp = Hp;
    }

    public int getPlayerHp(){
        return playerHp;
    }

    public void resetPlayerHp(){
        playerHp = 3;
    }

    public float getPlayerStartPosX(){
        return playerStartPosX;
    }

    public float getPlayerStartPosY(){
        return playerStartPosY;
    }
}
