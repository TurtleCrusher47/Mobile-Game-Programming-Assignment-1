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

    private Button btn_start;
    private Button btn_settings;
    private Button btn_quit;
    private Button btn_controls;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.settingspage);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_settings = (Button) findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(this);

        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(this);

        btn_controls = (Button) findViewById(R.id.btn_controls);
        btn_controls.setOnClickListener(this);

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
        return "SettingsPage";
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
