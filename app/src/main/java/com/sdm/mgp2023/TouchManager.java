package com.sdm.mgp2023;

import android.view.MotionEvent;

// Created by TanSiewLan2021

// Manages the touch events

public class TouchManager {
    public final static TouchManager Instance = new TouchManager();

    private TouchManager(){

    }

    public enum TouchState{
        NONE,
        DOWN,
        MOVE
    }

    private int posX, posY;

    private float y1 ,y2;

    private float swipeTimer = 0;
    private float minSwipeTime = 60;
    private TouchState status = TouchState.NONE; //Set to default as NONE

    public boolean HasTouch(){  // Check for a touch status on screen
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }

    public boolean IsDown(){
        return status == TouchState.DOWN;
    }

    public int GetPosX(){
        return posX;
    }

    public int GetPosY(){
        return posY;
    }

    public void Update(int _posX, int _posY, int _motionEventStatus){
        posX = _posX;
        posY = _posY;

        switch (_motionEventStatus){
            case MotionEvent.ACTION_DOWN:
                status = TouchState.DOWN;
                y1 = posY;

                break;

            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;

            case MotionEvent.ACTION_UP:
                y2 = posY;
                if(y1-y2 > 50 ){
                    if(PlayerStats.Instance.getJumpCount() > 0){
                        PlayerStats.Instance.setJumpTrue();
                        System.out.println("Jump");
                    }

                }

                status = TouchState.NONE;
                break;
        }
    }
}

