package com.example.mgp2023;

//written by Tania Lam

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeaderboardScreen extends Activity implements View.OnClickListener, StateBase
{
    public static LeaderboardScreen Instance = null;
    private TextView tv_leaderboard;
    private Button btn_back;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.leaderboardscreen);

        btn_back = (Button) findViewById(R.id.btn_back);

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("LeaderboardScreen");

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);

        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        if (view == btn_back)
        {
            finish();
            System.exit(0);
        }

    }

    @Override
    public String GetName() {
        return "LeaderboardScreen";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public void Update(float _dt) {

    }
}

