package com.example.mgp2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

public class GameSystem
{
    public final static GameSystem Instance = new GameSystem();

    // Game stuff
    private boolean isPaused = false;
    public boolean isLost = false;
    public boolean isWon = false;
    public double actuatorX;
    public double actuatorY;

    public int score = 0;

    public int health = 0;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {

        // 2. We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());

        /*
        // Please add state, NextPage.
        StateManager.Instance.AddState(new Nextpage());
        */

        // Please add state, MainGameSceneState.
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new WinScreen());
        StateManager.Instance.AddState(new LoseScreen());

        score = 0;
        health = 3;
    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

}