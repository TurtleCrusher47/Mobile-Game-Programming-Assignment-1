package com.example.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

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
        GameSystem.Instance.isLost = false;
        GameSystem.Instance.isWon = false;
        GameSystem.Instance.score = 0;
        GameSystem.Instance.health = 3;
        GameSystem.Instance.actuatorX = 0;
        GameSystem.Instance.actuatorY = 0;

        // 3. Create Background
        RenderBackground.Create();

        // Create joystick if the control mode is not accelerometer
        if (!GameSystem.Instance.accelerometer_control_mode)
        {
            JoystickEntity.Create();
        }

        TurtleEntity.Create();

        heart1 = HeartUIEntity.Create(80, 80);
        heart2 = HeartUIEntity.Create(200, 80);
        heart3 = HeartUIEntity.Create(320, 80);

        for (int i = 0; i < 11; i++)
        {
            SpikeEntity.Create(100 + (i * 200), 20);
            SpikeEntity.Create(100 + (i * 200), 1000);
        }

        SpikeEntity.Create(500, 220);
        SpikeEntity.Create(500, 420);

        SpikeEntity.Create(1100, 800);
        SpikeEntity.Create(1100, 220);

        SpikeEntity.Create(1700, 800);
        SpikeEntity.Create(1700, 600);

        TrashEntity.Create(100, 200);
        TrashEntity.Create(100, 700);
        TrashEntity.Create(800, 800);
        TrashEntity.Create(800, 300);
        TrashEntity.Create(1400, 500);
        TrashEntity.Create(2000, 300);

        TrashBinEntity.Create(2000, 800);


        RenderTextEntity.Create();

        PauseButtonEntity.Create();

        AudioManager.Instance.PlayAudio(R.raw.bgm, GameSystem.Instance.bgmVolume);

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
        if (!AudioManager.Instance.IsPlaying(R.raw.bgm))
        {
            AudioManager.Instance.PlayAudio(R.raw.bgm, 0.9f);
        }


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



