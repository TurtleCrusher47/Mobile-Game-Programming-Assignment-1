package com.example.mgp2023;

//written by Tania Lam

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeaderboardScreen extends Activity implements View.OnClickListener, StateBase
{
    public static LeaderboardScreen Instance = null;
    private TextView tv_leaderboard;
    private Button btn_back;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.leaderboardscreen);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        tv_leaderboard = (TextView) findViewById(R.id.tv_leaderboard);

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("LeaderboardScreen");

        int highscore = GameSystem.Instance.GetIntFromSave("HIGHSCORE");
        tv_leaderboard.setText("Highscore: " + highscore);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        if (view == btn_back)
        {
            Log.d("leaderboard", "onClick: " + GameSystem.Instance.lastScreen);

            if (GameSystem.Instance.lastScreen == "WinScreen")
            {
                intent.setClass(this, WinScreen.class);
                StateManager.Instance.ChangeState("WinScreen");
            }
            else if (GameSystem.Instance.lastScreen == "LoseScreen")
            {
                intent.setClass(this, LoseScreen.class);
                StateManager.Instance.ChangeState("LoseScreen");
            }

            startActivity(intent);

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

