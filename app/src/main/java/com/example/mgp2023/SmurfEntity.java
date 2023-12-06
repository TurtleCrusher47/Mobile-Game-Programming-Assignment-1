package com.example.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;
import java.util.Random;

public class SmurfEntity implements EntityBase, ICollidableCircle
{
	// 1. Declare the use of spritesheet using Sprite class
    // Usual method of loading a bmp / image
    public Bitmap bmp = null;
    public Sprite spritesheet = null;
    private boolean isDone = false;
    private boolean isInit = false;

	 // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;
    
	 // For use with the TouchManager.class
    private boolean hasTouched = false;

    int ScreenWidth, ScreenHeight;

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
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.smurf_sprite), 4, 4, 16);

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

        // 4. Update spritesheet
        spritesheet.Update(_dt);

        // 5. Deal with the touch on screen for interaction of the image using collision check
        if (TouchManager.Instance.HasTouch())
        {
            // 6. Check collision
            float imgRadius = spritesheet.GetWidth() * 0.5f;

            // Other than check the finger that touch on the screen, the x, y = the image area hence meant this is the image I want to interact with, we
            // also want to touch and hold and drag this image
            if (Collision.CircleToCircle(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) || hasTouched)
            {
                // Collided
                hasTouched = true;

                // 7. Drag the sprite around the screen
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();

                xPos += xDir * _dt;
                yPos += yDir * _dt;
            }
        }

    }

    @Override
    public void Render(Canvas _canvas) {
       
        // This is for our sprite animation!
        spritesheet.Render(_canvas, (int)xPos, (int)yPos);

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.SMURF_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static SmurfEntity Create()
    {
        SmurfEntity result = new SmurfEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_SMURF;}

    @Override
    public String GetType() {
        return "SmurfEntity";
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
    public float GetRadius() {
        return spritesheet.GetHeight() * 0.5f;
    }

    @Override
    public void OnHit(ICollidableCircle _other)
    {
        // This allows you to check collision between 2 entities.
        // Star Entity can cause harm to the player when hit.
        // If hit by star, you can play an audio, or have a visual feedback or
        // physical feedback.
        // SetIsDone(true) --> allows you to delete the entity from the screen.

        System.out.println("Hit");
        //if (_other.GetType() == "StarEntity") //Another Entity
        {
            //SetIsDone(true);
            //Play an audio
        }
    }

    @Override
    public void OnHit(ICollidableBox _other)
    {
        System.out.println("Hit");
    }

}
