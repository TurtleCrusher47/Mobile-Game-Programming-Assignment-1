package com.example.mgp2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.SurfaceView;
import android.content.SharedPreferences;

public class GameSystem
{
    public final static GameSystem Instance = new GameSystem();
    public final static String SHARED_PREF_ID = "GameSaveFile";

    // Game stuff
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;
    private boolean isPaused = false;
    public boolean isLost = false;
    public boolean isWon = false;
    public boolean accelerometer_control_mode = false;

    public boolean hasHat, hasTie = false;
    public double actuatorX;
    public double actuatorY;

    public int score = 0;
    public int currentGameScore = 0;

    public int health = 0;

    public int bgmVolume = 100;

    public float bgmVolumeFloat = 1.0f;
    public int sfxVolume = 100;

    public float sfxVolumeFloat = 1.0f;
    public int masterVolume = 100;

    public float masterVolumeFloat = 1.0f;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }


    public void Init(SurfaceView _view)
    {
        sharedPreferences = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID, 0);


        // 2. We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new SettingsPage());
        StateManager.Instance.AddState(new ShopScreen());

        /*
        // Please add state, NextPage.
        StateManager.Instance.AddState(new Nextpage());
        */

        // Please add state, MainGameSceneState.
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new WinScreen());
        StateManager.Instance.AddState(new LoseScreen());
        StateManager.Instance.AddState(new LeaderboardScreen());

        score = 0;
        currentGameScore = 0;
        health = 3;

        SharedPreferences sharedPreferences = _view.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        hasHat = sharedPreferences.getBoolean("HAT", false);
        hasTie = sharedPreferences.getBoolean("TIE", false);
    }

    public void Update(float _deltaTime)
    {

    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

    public void SaveEditBegin()
    {
        // Only allow if not already editing
        if (editor != null)
        {
            return;
        }

        editor = sharedPreferences.edit();
    }

    public void SaveEditEnd()
    {
        if (editor == null)
        {
            return;
        }

        editor.commit();
        // To ensure other functions will fail once commit is done
        editor = null;
    }

    public void SetIntInSave(String _key, int _value)
    {
        if (editor == null)
        {
            return;
        }

        editor.putInt(_key, _value);
    }

    public int GetIntFromSave(String _key)
    {
        return sharedPreferences.getInt(_key, 0);
    }

    public void SetBoolInSave(String _key, boolean _value)
    {
        if (editor == null)
        {
            return;
        }

        editor.putBoolean(_key, _value);
    }

    public boolean GetBoolFromSave(String _key)
    {
        return sharedPreferences.getBoolean(_key, false);
    }
}