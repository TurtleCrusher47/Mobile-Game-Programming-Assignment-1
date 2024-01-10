package com.example.mgp2023;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class TurtleEntity implements EntityBase, ICollidableBox, SensorEventListener
{
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / UpdateThread.targetFPS;
    // 1. Declare the use of spritesheet using Sprite class
    // Usual method of loading a bmp / image
    public Bitmap bmp = null;
    public Sprite spritesheet = null;

    private boolean isDone = false;
    private boolean isInit = false;

    // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;
    private double velocityX;
    private double velocityY;

    private Vibrator vibrator;

    float imgWidth;
    float imgHeight;

    // For use with the TouchManager.class
    private boolean hasTouched = false;

    private boolean canTakeDamage = true;
    private float damageTimer = 0;

    int screenWidth, screenHeight;
    private static final String TAG = "Turtle";

    private SensorManager sensorManager;
    private Sensor sensor;
    private float[] values = {0, 0, 0};
    private float[] gravity = {0, 0, 0};
    private float[] linear_acceleration = {0, 0, 0};

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
        sensorManager = (SensorManager) _view.getContext().getSystemService((Context.SENSOR_SERVICE));
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        // New method using our own resource manager : Returns pre-loaded one if exists
        // 2. Loading spritesheet
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.burgle_image), 2, 2, 4);

        // If you want to load a certain frame for animation --> SetAnimationFrames(int _start, int _end)
        // spritesheet.SetAnimationFrames(1,15);

        // 3. Get some random position of x and y
        // Random generator under the java utility library
        Random ranGen = new Random();

        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;


        xPos = 100;
        yPos = screenHeight / 2;

        xDir = ranGen.nextFloat() * 100.0f - 50.0f;
        yDir = ranGen.nextFloat() * 100.0f - 50.0f;

        imgWidth = spritesheet.GetWidth()/2 - 80;
        imgHeight = spritesheet.GetHeight()/6;

        vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);

        //Log.d(TAG, " turtle width: " + imgWidth);
        //Log.d(TAG, " turtle height: " + imgHeight);

        isInit = true;

    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused())
            return;

        // 4. Update spritesheet
        spritesheet.Update(_dt);

        velocityX = GameSystem.Instance.actuatorX * MAX_SPEED;
        velocityY = GameSystem.Instance.actuatorY * MAX_SPEED;

        xPos += velocityX;
        yPos += velocityY;

        if (!canTakeDamage)
        {
            damageTimer += _dt;

            if (damageTimer >= 3f)
            {
                canTakeDamage = true;
                damageTimer = 0;
            }
        }
//        MainGameSceneState.joystickEntity

        // 5. Deal with the touch on screen for interaction of the image using collision check
//        if (TouchManager.Instance.HasTouch())
//        {
//            // 6. Check collision
//            imgWidth = spritesheet.GetWidth();
//            imgHeight = spritesheet.GetHeight();
//
//            // Other than check the finger that touch on the screen, the x, y = the image area hence meant this is the image I want to interact with, we
//            // also want to touch and hold and drag this image
//            if (Collision.CircleToBox(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgWidth, imgHeight) || hasTouched)
//            {
//                // Collided
//                hasTouched = true;
//
//                // 7. Drag the sprite around the screen
//                xPos = TouchManager.Instance.GetPosX();
//                yPos = TouchManager.Instance.GetPosY();
//
//                xPos += xDir * _dt;
//                yPos += yDir * _dt;
//            }
//        }
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
        return LayerConstants.TURTLE_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static TurtleEntity Create()
    {
        TurtleEntity result = new TurtleEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_TURTLE);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_TURTLE;}

    @Override
    public String GetType() {
        return "TurtleEntity";
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
    public float GetWidth() { return imgWidth; }

    @Override
    public float GetHeight() { return imgHeight; }

    @Override
    public void OnHit(ICollidableBox _other) {

        if (_other.GetType() == "SpikeEntity") //Another Entity
        {
            if (canTakeDamage)
            {
                canTakeDamage = false;
                GameSystem.Instance.health -= 1;

                xPos = 100;
                yPos = screenHeight / 2;

                AudioManager.Instance.PlayAudio(R.raw.hurt, 2.0f);

                StartVibrate();
            }

        }

    }
    @Override
    public void OnHit(ICollidableCircle _other) {
        // This allows you to check collision between 2 entities.
        // SetIsDone(true) --> allows you to delete the entity from the screen.

        if (_other.GetType() == "TrashEntity") //Another Entity
        {
            //Play an audio
            GameSystem.Instance.score += 10;

            AudioManager.Instance.PlayAudio(R.raw.collect, 3.0f);

            StartVibrate();
        }
        //Log.d(TAG, "Turtle Hit");
    }

    public void StartVibrate()
    {
        if (Build.VERSION.SDK_INT >= 26)
        {
            vibrator.vibrate(VibrationEffect.createOneShot(150, 10));
        }

        else
        {
            long pattern[] = {0, 50, 0};
            vibrator.vibrate(pattern, -1);
        }
    }

    public void StopVibrate()
    {
        vibrator.cancel();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final float alpha = 0.8f;

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        values = event.values;
        System.out.println(values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
