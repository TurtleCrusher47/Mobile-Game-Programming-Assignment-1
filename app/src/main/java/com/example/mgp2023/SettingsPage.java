package com.example.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.widget.SwitchCompat;

public class SettingsPage extends Activity implements OnClickListener, StateBase {

    public static SettingsPage Instance = null;

    private Button btn_back;
    private SwitchCompat switch_controls;
    private SeekBar seekbar_mastervolume;
    private SeekBar seekbar_bgm;
    private SeekBar seekbar_sfx;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.settingspage);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        seekbar_mastervolume = (SeekBar) findViewById(R.id.seekbar_mastervolume);
        seekbar_mastervolume.setOnClickListener(this);

        seekbar_bgm = (SeekBar) findViewById(R.id.seekbar_bgm);
        seekbar_bgm.setOnClickListener(this);

        seekbar_sfx = (SeekBar) findViewById(R.id.seekbar_sfx);
        seekbar_sfx.setOnClickListener(this);

        switch_controls = (SwitchCompat) findViewById(R.id.controls_switch);
        switch_controls.setOnClickListener(this);

        // Make this known to the StateManager
        StateManager.Instance.AddState(new SettingsPage());

        Instance = this;

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("SettingsPage");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
            StateManager.Instance.ChangeState("Mainmenu");
        }
        startActivity(intent);
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
