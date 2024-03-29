package com.example.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.widget.SwitchCompat;

public class SettingsPage extends Activity implements OnClickListener, SeekBar.OnSeekBarChangeListener, StateBase {

    public static SettingsPage Instance = null;

    private Button btn_back;
    private SeekBar seekbar_mastervolume;
    private SeekBar seekbar_bgm;
    private SeekBar seekbar_sfx;
    private SwitchCompat switch_controls;

    private int masterVolume, bgmVolume, sfxVolume;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.settingspage);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        seekbar_mastervolume = (SeekBar) findViewById(R.id.seekbar_mastervolume);
        seekbar_mastervolume.setOnSeekBarChangeListener(this);

        seekbar_bgm = (SeekBar) findViewById(R.id.seekbar_bgm);
        seekbar_bgm.setOnSeekBarChangeListener(this);

        seekbar_sfx = (SeekBar) findViewById(R.id.seekbar_sfx);
        seekbar_sfx.setOnSeekBarChangeListener(this);

        switch_controls = (SwitchCompat) findViewById(R.id.controls_switch);
        switch_controls.setOnClickListener(this);

        // Make this known to the StateManager
        StateManager.Instance.AddState(new SettingsPage());

        Instance = this;

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("SettingsPage");

        masterVolume = GameSystem.Instance.GetIntFromSave("MASTER");
        bgmVolume = GameSystem.Instance.GetIntFromSave("BGM");
        sfxVolume = GameSystem.Instance.GetIntFromSave("SFX");

        seekbar_mastervolume.setProgress(masterVolume);
        seekbar_bgm.setProgress(bgmVolume);
        seekbar_sfx.setProgress(sfxVolume);

        Log.d("Settings", String.valueOf(masterVolume));
    }

    @Override
    public void onClick(View view) {

        if (view == btn_back) {
            Intent intent = new Intent();
            intent.setClass(this, Mainmenu.class);
            StateManager.Instance.ChangeState("Mainmenu");
            startActivity(intent);
        }
        else if (view == switch_controls)
        {
            GameSystem.Instance.accelerometer_control_mode = switch_controls.isChecked();
//            System.out.println(GameSystem.Instance.accelerometer_control_mode);
        }
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == seekbar_mastervolume) {
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("MASTER", seekbar_mastervolume.getProgress());
            GameSystem.Instance.SaveEditEnd();
        }

        else if (seekBar == seekbar_bgm)
        {
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("BGM", seekbar_bgm.getProgress());
            GameSystem.Instance.SaveEditEnd();
        }

        else if (seekBar == seekbar_sfx)
        {
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("SFX", seekbar_sfx.getProgress());
            GameSystem.Instance.SaveEditEnd();

            Log.d("Settings", String.valueOf(GameSystem.Instance.GetIntFromSave("SFX")));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
