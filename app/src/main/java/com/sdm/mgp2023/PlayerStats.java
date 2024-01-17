package com.sdm.mgp2023;

public class PlayerStats {
    public final static PlayerStats Instance = new PlayerStats();
    private int playerScore = 0;
    private  int playerHp = 3;
    private float playerStartPosX = 500;
    private  float playerStartPosY = -100;
    private boolean jump = false;

    private int jumpCount = 2;

    private boolean reset = false;

    private int finalPlayerScore = 0;
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

    public void setJumpFalse(){
        jump = false;
    }

    public void setJumpTrue(){
        jump = true;
    }

    public boolean getJump(){
        return jump;
    }

    public boolean getReset(){
        return reset;
    }
    public void setReset(boolean _reset){
        reset = _reset;
    }

    public void minusJumpCount(){
        jumpCount--;
    }

    public int getJumpCount(){
        return jumpCount;
    }
    public void resetJumpCount(){
        jumpCount = 2;
    }

    public void setFinalPlayerScore(int _score){
        finalPlayerScore = _score;
    }

    public int getFinalPlayerScore(){
        return finalPlayerScore;
    }
}
