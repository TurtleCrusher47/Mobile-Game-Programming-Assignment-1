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
    private Button btn_restart;
    private Button btn_quit;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.losescreen);

        btn_restart = (Button) findViewById(R.id.btn_restart);
        btn_start.setOnClickListener(this);
        // Set Listener to this button --> Start Button
//        btn_back = (Button) findViewById(R.id.btn_back);
//        btn_back.setOnClickListener(this);
        // Set Listener to this button --> Back Button

        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(this);

        // Make this known to the StateManager
        StateManager.Instance.AddState(new Mainmenu());

        Instance = this;

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("MainMenu");
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public String GetName() {
        return null;
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
