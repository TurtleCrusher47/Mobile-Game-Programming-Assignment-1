package com.example.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class TrashEntity implements EntityBase, ICollidableCircle{

    // 1. Declare the use of spritesheet using Sprite class
    // Usual method of loading a bmp / image
    public Bitmap bmpP = null;
    public Bitmap scaledBmpP = null;

    private boolean isDone = false;
    private boolean isInit = false;

    // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;

    float imgRadius;

    // For use with the TouchManager.class
    private boolean hasTouched = false;

    int screenWidth, screenHeight;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        // New method using our own resource manager : Returns pre-loaded one if exists
        // 2. Loading spritesheet
        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.plasticbag);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        scaledBmpP = Bitmap.createScaledBitmap(bmpP, screenWidth/10, screenHeight/10, true);
        imgRadius = scaledBmpP.getHeight() * 0.5f;

        // If you want to load a certain frame for animation --> SetAnimationFrames(int _start, int _end)
        // spritesheet.SetAnimationFrames(1,15);

        // 3. Get some random position of x and y
        // Random generator under the java utility library
        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();

        xDir = ranGen.nextFloat() * 100.0f - 50.0f;
        yDir = ranGen.nextFloat() * 100.0f - 50.0f;



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

    public static TrashEntity Create()
    {
        TrashEntity result = new TrashEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_TRASH);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_TRASH;}

    @Override
    public String GetType() {
        return "TrashEntity";
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
    public float GetRadius() { return imgRadius; }

    @Override
    public void OnHit(ICollidableBox _other) {

        if (_other.GetType() == "TurtleEntity") //Another Entity
        {
            //SetIsDone(true);
            //Play an audio

            GameSystem.Instance.score += 10;
            Log.d(TAG, "Collided with" + _other.GetType());

        }

    }

    private static final String TAG = "Trash";
    @Override
    public void OnHit(ICollidableCircle _other) {
        // This allows you to check collision between 2 entities.
        // Star Entity can cause harm to the player when hit.
        // If hit by star, you can play an audio, or have a visual feedback or
        // physical feedback.
        // SetIsDone(true) --> allows you to delete the entity from the screen.

        if (_other.GetType() == "TurtleEntity") //Another Entity
        {
            //SetIsDone(true);
            //Play an audio

            GameSystem.Instance.score += 10;
            Log.d(TAG, "Collided with" + _other.GetType());
        }
        Log.d(TAG, "Trash Hit");
    }
}
