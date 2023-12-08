package com.example.mgp2023;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;

    HeartUIEntity heart1, heart2, heart3;
    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // 3. Create Background
        RenderBackground.Create();

        // Add more entities
        JoystickEntity.Create();

        TurtleEntity.Create();

        TrashEntity.Create();

        heart1 = HeartUIEntity.Create(80, 80);
        heart2 = HeartUIEntity.Create(200, 80);
        heart3 = HeartUIEntity.Create(320, 80);

        SpikeEntity.Create();

        RenderTextEntity.Create();

        PauseButtonEntity.Create();

        AudioManager.Instance.PlayAudio(R.raw.bgm, 0.9f);

        // Example to include another Renderview for Pause Button
    }

    @Override
    public void OnExit()
    {
        // 4. Clear any instance instantiated via EntityManager
        EntityManager.Instance.Clean();
     
        // 5. Clear or end any instance instantiated via GamePage
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    @Override
    public void Update(float _dt)
    {

        EntityManager.Instance.Update(_dt );

        if (GameSystem.Instance.health < 3)
        {
            heart3.SetIsShown(false);
        }

        if (GameSystem.Instance.health < 2)
        {
            heart2.SetIsShown(false);
        }

        if (GameSystem.Instance.health < 1)
        {
            heart1.SetIsShown(false);
        }

//        if (TouchManager.Instance.IsDown())
//        {
//            //6. Example of touch on screen in the main game to trigger back to Main menu
//            StateManager.Instance.ChangeState("MainMenu");
//        }
    }
}



