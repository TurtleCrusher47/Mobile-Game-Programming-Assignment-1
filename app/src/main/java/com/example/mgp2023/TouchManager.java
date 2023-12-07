package com.example.mgp2023;

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
    private TouchState status = TouchState.NONE; //Set to default as NONE

    // Check for a touch status on screen
    public boolean HasTouch(){
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }

    public boolean HasMove() {
        return status == TouchState.MOVE;
    }

    public boolean IsDown(){
        return status == TouchState.DOWN;
    }

    public boolean IsUp()
    {
        return status == TouchState.NONE;
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
                break;

            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;

            case MotionEvent.ACTION_UP:
                status = TouchState.NONE;
                break;
        }
    }
}

