package com.example.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PauseButtonEntity implements EntityBase
{
    private boolean isDone = false;
    private boolean isInit = false;

    private Bitmap bmpP = null;
    private Bitmap bmpP1 = null;

    //scaled versions of bitmap
    private Bitmap scaledBmpP = null;
    private Bitmap scaledBmpP1 = null;

    private int xPos, yPos = 0;
    private int screenWidth, screenHeight = 0;
    private boolean Paused = false;
    private float buttonDelay = 0;

    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        //init images to be used
        //load images
        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.pause);
        bmpP1 = ResourceManager.Instance.GetBitmap(R.drawable.pause1);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        scaledBmpP = Bitmap.createScaledBitmap(bmpP, screenHeight/8, screenHeight/8, true);
        scaledBmpP1 = Bitmap.createScaledBitmap(bmpP1, screenHeight/8, screenHeight/8, true);

        xPos = screenWidth - 100;
        yPos = 100;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {

        buttonDelay += _dt;

        if (TouchManager.Instance.HasTouch())
        {
            if (TouchManager.Instance.IsDown() && !Paused)
            {
                //checl collision of button
                float imgRadius = scaledBmpP.getHeight() * 0.5f;

                if (Collision.CircleToCircle(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) && buttonDelay >= 0.25)
                {
                    Paused = true;


                    if (PauseConfirmDialogueFragment.isShown)
                    {
                        return;
                    }

                    PauseConfirmDialogueFragment newPause = new PauseConfirmDialogueFragment();
                    newPause.show(GamePage.Instance.getSupportFragmentManager(), "PauseConfirm");

                }
            }
        }

        else
            Paused = false;

    }

    @Override
    public void Render(Canvas _canvas) {

        if (!Paused)
            _canvas.drawBitmap(scaledBmpP, xPos - scaledBmpP.getWidth() *0.5f, yPos -scaledBmpP.getHeight() *0.5f, null);
        else
            _canvas.drawBitmap(scaledBmpP1, xPos - scaledBmpP.getWidth() *0.5f, yPos -scaledBmpP.getHeight() *0.5f, null);

    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return 0;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    public static PauseButtonEntity Create()
    {
        PauseButtonEntity result = new PauseButtonEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_PAUSE;}

    @Override
    public String GetType() {
        return "PauseButtonEntity";
    }

}
