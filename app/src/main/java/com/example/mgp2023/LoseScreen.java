package com.example.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class LoseScreen extends Activity implements OnClickListener, StateBase
{
    public static LoseScreen Instance = null;
    private Button btn_restart;
    private Button btn_quit;
    private Button btn_leaderboard;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.losescreen);

        btn_restart = (Button) findViewById(R.id.btn_restart);
        btn_restart.setOnClickListener(this);
        // Set Listener to this button --> Start Button
//        btn_back = (Button) findViewById(R.id.btn_back);
//        btn_back.setOnClickListener(this);
        // Set Listener to this button --> Back Button

        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(this);

        btn_leaderboard = (Button) findViewById(R.id.btn_leaderboard);
        btn_leaderboard.setOnClickListener(this);

        // Make this known to the StateManager
        StateManager.Instance.AddState(new LoseScreen());

        Instance = this;

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("LoseScreen");

        GameSystem.Instance.lastScreen = "LoseScreen";
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view == btn_restart)
        {
            //intent -> to set to another class which is another page or screen to be
            //launch.
            //Equal to change screen
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("MainGame");
        }
        else if (view == btn_quit)
        {
            intent.setClass(this, Mainmenu.class);
            StateManager.Instance.ChangeState("MainMenu");
        }

        else if (view == btn_leaderboard)
        {
            intent.setClass(this, LeaderboardScreen.class);
            StateManager.Instance.ChangeState("LeaderboardScreen");
        }
        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "LoseScreen";
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
