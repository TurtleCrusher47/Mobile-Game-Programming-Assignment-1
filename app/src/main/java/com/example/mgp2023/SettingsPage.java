package com.example.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class SettingsPage extends Activity implements OnClickListener, StateBase {

    public static SettingsPage Instance = null;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.mainmenu);

//        btn_start = (Button) findViewById(R.id.btn_start);
//        btn_start.setOnClickListener(this);

        // Make this known to the StateManager
        StateManager.Instance.AddState(new SettingsPage());

        Instance = this;

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("SettingsPage");
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
