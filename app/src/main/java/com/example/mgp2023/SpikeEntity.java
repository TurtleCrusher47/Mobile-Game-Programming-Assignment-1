package com.example.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class SpikeEntity implements EntityBase, ICollidableBox{

    // Usual method of loading a bmp / image
    public Bitmap bmpP = null;
    public Bitmap scaledBmpP = null;

    private boolean isDone = false;
    private boolean isInit = false;

    // Variables to be used or can be used.
    public float xPos, yPos;

    float imgWidth, imgHeight;

    int screenWidth, screenHeight;
    private static final String TAG = "Spike";


    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view)
    {
        // New method using our own resource manager : Returns pre-loaded one if exists
        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.spike);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        scaledBmpP = Bitmap.createScaledBitmap(bmpP, screenWidth/10, screenWidth/10, true);

        imgWidth = screenWidth/12;
        imgHeight = screenWidth/12 - 80;


        // 3. Get some random position of x and y
        // Random generator under the java utility library
        Random ranGen = new Random();

//        xPos = ranGen.nextFloat() * _view.getWidth();
//        yPos = ranGen.nextFloat() * _view.getHeight();

        isInit = true;

    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused())
            return;

    }

    @Override
    public void Render(Canvas _canvas) {

        _canvas.drawBitmap(scaledBmpP, xPos - scaledBmpP.getWidth() *0.5f, yPos -scaledBmpP.getHeight() *0.5f, null);

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.ITEM_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static SpikeEntity Create(int _xPos, int _yPos)
    {
        SpikeEntity result = new SpikeEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SPIKE);
        result.xPos = _xPos;
        result.yPos = _yPos;
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_SPIKE;}

    @Override
    public String GetType() {
        return "SpikeEntity";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetWidth(){ return imgWidth; }
    public float GetHeight(){ return imgHeight; }

    @Override
    public void OnHit(ICollidableBox _other) {

    }

    @Override
    public void OnHit(ICollidableCircle _other) {

    }
}
