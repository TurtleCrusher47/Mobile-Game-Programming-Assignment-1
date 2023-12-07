package com.example.mgp2023;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class JoystickEntity implements EntityBase {

//    public final static JoystickEntity Instance = new JoystickEntity();
    private int outerCircleRadius;
    private int innerCircleRadius;
    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private double actuatorX;
    private double actuatorY;
    private double joystickCenterToTouchDistance;

    private boolean isPressed = false;

    public JoystickEntity(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius)
    {
        // Outer and inner circle that make up the joystick
        outerCircleCenterPositionX = centerPositionX;
        outerCircleCenterPositionY = centerPositionY;
        innerCircleCenterPositionX = centerPositionX;
        innerCircleCenterPositionY = centerPositionY;

        // Radii of circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        // Paint of circles
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    @Override
    public void Init(SurfaceView _view) {

    }

    @Override
    public void Update(float _dt)
    {
        UpdateInnerCirclePosition();

        if (TouchManager.Instance.IsDown())
        {
            if (IsPressed((double) TouchManager.Instance.GetPosX(), (double) TouchManager.Instance.GetPosY()))
            {
                isPressed = true;

                System.out.println("Down");
            }
        }
        if (TouchManager.Instance.HasMove())
        {
            if (GetIsPressed())
            {
                SetActuator((double)TouchManager.Instance.GetPosX(), (double) TouchManager.Instance.GetPosY());

                System.out.println("Move");
            }
        }
        if (TouchManager.Instance.IsUp())
        {
            isPressed = false;
            ResetActuator();

            System.out.println("Up");
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);

        _canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);
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

    private void UpdateInnerCirclePosition()
    {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY * outerCircleRadius);
    }

    public boolean IsPressed(double touchPositionX, double touchPositionY)
    {
        joystickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPositionX - TouchManager.Instance.GetPosX(), 2) +
                Math.pow(outerCircleCenterPositionY - TouchManager.Instance.GetPosY(), 2)
        );
        return joystickCenterToTouchDistance < outerCircleRadius;
    }

    public void SetIsPressed(boolean isPressed)
    {
        this.isPressed = isPressed;
    }

    public boolean GetIsPressed()
    {
        return isPressed;
    }

    public void SetActuator(double touchPositionX, double touchPositionY)
    {
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY = touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if(deltaDistance < outerCircleRadius)
        {
            actuatorX = deltaX / outerCircleRadius;
            actuatorY = deltaY / outerCircleRadius;
        }
        else
        {
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }
    }

    public void ResetActuator()
    {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public static JoystickEntity Create()
    {
        JoystickEntity result = new JoystickEntity(275, 700, 70, 40);
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_JOYSTICK);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    @Override
    public String GetType() {
        return null;
    }
}
