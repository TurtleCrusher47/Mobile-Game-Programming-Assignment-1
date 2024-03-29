package com.example.mgp2023;

// Create a GamePage is an activity class used to hold the GameView which will have a surfaceview

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class GamePage extends FragmentActivity
{
    public static GamePage Instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Get shared preferences

        Instance = this;

        setContentView(new GameView(this)); // Surfaceview = GameView

        //Log.d("Settings", String.valmasterVolume));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // WE are hijacking the touch event into our own system
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x, y, event.getAction());

        return true;
    }

    public void CheckGameState()
    {
//        System.out.println("check game state");

        if (GameSystem.Instance.isLost)
        {
            Intent intent = new Intent(GamePage.this, LoseScreen.class);
            StateManager.Instance.ChangeState("LoseScreen");

//            System.out.println(GameSystem.Instance.currentGameScore);
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("Score", GameSystem.Instance.currentGameScore);
            GameSystem.Instance.SaveEditEnd();

            startActivity(intent);
        }

        if (GameSystem.Instance.isWon)
        {
            Intent intent = new Intent(GamePage.this, WinScreen.class);
            StateManager.Instance.ChangeState("WinScreen");

            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("Score", GameSystem.Instance.currentGameScore);
            GameSystem.Instance.SaveEditEnd();

            startActivity(intent);
        }
    }
}

