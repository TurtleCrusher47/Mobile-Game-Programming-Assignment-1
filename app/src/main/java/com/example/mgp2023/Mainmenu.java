package com.example.mgp2023;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Mainmenu extends Activity implements OnClickListener, StateBase{

    public static Mainmenu Instance = null;

    //Define buttons. We have 2 buttons. Start and Back
    private Button btn_start;
    private Button btn_back;
    private Button btn_quit;

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.mainmenu);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        // Set Listener to this button --> Start Button
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
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
    //Invoke a callback event in the view
    //Intent = action to be performed
    //Intent is an object provides runtime binding
    //You have to new an instance of this object to use it
    //Becos we need to check if start or back button is clicked/ touched on the
    //screen, then after
    //what do we want to do.
    //If start button is clicked, we go to Splash page.
    //If back button is clicked, we go to main menu.
    public void onClick (View v) {
        Intent intent = new Intent();
        if (v == btn_start)
        {
            //intent -> to set to another class which is another page or screen to be
            //launch.
            //Equal to change screen
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("MainGame");
        }
        else if (v == btn_back)
        {
            intent.setClass(this, Nextpage.class);
            StateManager.Instance.ChangeState("NextPage");
        }
        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "MainMenu";
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

//    @Override
//    protected void onPause(){
//        super.onPause();
//    }
//    @Override
//    protected void onStop(){
//        super.onStop();
//    }
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//    }
}
