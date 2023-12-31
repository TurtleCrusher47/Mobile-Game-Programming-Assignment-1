package com.example.mgp2023;

import android.util.Log;

public class Collision
{
    public static boolean CircleToCircle(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }

    public static boolean BoxToBox(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2)
    {
        if (Math.abs(x1 - x2) < width1 + width2)
        {
            if (Math.abs(y1 - y2) < height1 + height2)
            {
                return true;
            }
        }
        return false;
    }

    private static final String TAG = "Collision";

    public static boolean CircleToBox(float circleX, float circleY, float radius, float boxX, float boxY, float boxWidth, float boxHeight)
    {

        float distanceX = Math.abs(circleX - boxX);
        float distanceY = Math.abs(circleY - boxY);

        //Log.d(TAG, "CircleToBox X: " + distanceX);
        //Log.d(TAG, "CircleToBox Y: " + distanceY);

        if (distanceX > boxWidth / 2 + radius)
        {
            //Log.d(TAG, "X false: " + distanceX + " / " + boxWidth / 2 + radius);
            return false;
        }
        if (distanceY > boxHeight / 2 + radius)
        {
            //Log.d(TAG, "Y false: " + distanceY + " / " + boxHeight / 2 + radius);
            return false;
        }

        if (distanceX <= boxWidth / 2)
        {
            //Log.d(TAG, "X true: " + distanceX + " / " + boxWidth / 2);
            return true;
        }
        if (distanceY <= boxHeight / 2)
        {
            //Log.d(TAG, "Y true: " + distanceY + " / " + boxHeight / 2);

            return true;
        }

        float cornerDistanceSquared = ((distanceX - boxWidth / 2) * (distanceX - boxWidth / 2)) + ((distanceY - boxHeight / 2) * (distanceY - boxHeight / 2));
        Log.d (TAG , cornerDistanceSquared + " / " +  (radius * radius));
        return (cornerDistanceSquared <= (radius * radius));



    }
}
