package com.example.mgp2023;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

//written by Tania Lam

public class ShopScreen extends Activity implements View.OnClickListener, StateBase
{
    public static ShopScreen Instance = null;
    private RelativeLayout hatGroup, tieGroup;
    private TextView bought, bought2;

    private TextView tv_coins;
    private Button btn_back;

    private boolean hasHat, hasTie;

    private int coins;


    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.shopscreen);

        btn_back = (Button) findViewById(R.id.btn_back);

        hatGroup = (RelativeLayout) findViewById((R.id.hatGroup));
        tieGroup = (RelativeLayout) findViewById((R.id.tieGroup));

        bought = (TextView) findViewById((R.id.bought));
        bought2 = (TextView) findViewById((R.id.bought2));

        tv_coins = (TextView) findViewById((R.id.tv_coins));

        StateManager.Instance.Init(new SurfaceView(this));
        GameSystem.Instance.Init(new SurfaceView(this));
        StateManager.Instance.Start("ShopScreen");

        SharedPreferences sharedPreferences = getSharedPreferences("GameSaveFile"   , Context.MODE_PRIVATE);
        coins = GameSystem.Instance.GetIntFromSave("COINS");
        hasHat = GameSystem.Instance.GetBoolFromSave("HAT");
        hasTie = GameSystem.Instance.GetBoolFromSave("TIE");

        //coins = 100;

        tv_coins.setText("$"+coins);

        if (hasHat == false)
        {
            bought.setVisibility(View.GONE);
        }

        if (hasTie == false)
        {
            bought2.setVisibility(View.GONE);
        }

        btn_back.setOnClickListener(this);
        hatGroup.setOnClickListener(this);
        tieGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        if (view == btn_back)
        {
            finish();
            System.exit(0);
        }

        else if (view == hatGroup)
        {
            if (hasHat == false && coins >= 50)
            {
                coins -= 50;
                tv_coins.setText("$"+coins);
                bought.setVisibility(View.VISIBLE);
                hasHat = true;

                //update coins amt in shared prefs
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetIntInSave("COINS", coins);
                GameSystem.Instance.SaveEditEnd();

                //update hasHat bool in shared prefs
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetBoolInSave("HAT", true);
                GameSystem.Instance.SaveEditEnd();
            }
        }

        else if (view == tieGroup)
        {
            if (hasTie == false && coins >= 50)
            {
                coins -= 50;
                tv_coins.setText("$"+coins);
                bought2.setVisibility(View.VISIBLE);
                hasTie = true;

                //update coins amt in shared prefs
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetIntInSave("COINS", coins);
                GameSystem.Instance.SaveEditEnd();

                //update hasHat bool in shared prefs
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetBoolInSave("TIE", true);
                GameSystem.Instance.SaveEditEnd();
            }
        }
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
