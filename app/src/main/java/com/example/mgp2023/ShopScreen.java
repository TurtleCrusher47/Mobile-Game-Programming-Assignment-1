package com.example.mgp2023;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

//written by Tania Lam

public class ShopScreen extends Activity implements View.OnClickListener, StateBase
{
    public static ShopScreen Instance = null;
    private Button btn_restart;
    private Button btn_quit;

    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.shopscreen);

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("ShopScreen");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "ShopScreen";
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
