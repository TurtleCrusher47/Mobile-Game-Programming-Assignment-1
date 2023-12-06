package com.example.mgp2023;

public class Collision
{
    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
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

    public static boolean BoxToBox(float x1, float y1, float sizeX1, float sizeY1, float x2, float y2, float sizeX2, float sizeY2)
    {
        if (Math.abs(x1 - x2) < sizeX1 + sizeX2)
        {
            if (Math.abs(y1 - y2) < sizeY1 + sizeY2)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean CircleToBox(float circleX, float circleY, float radius, float boxX, float boxY, float boxWidth, float boxHeight)
    {
        float distanceX = Math.abs(circleX - boxWidth);
        float distanceY = Math.abs(circleY - boxHeight);

        if (distanceX > boxWidth / 2 + radius)
            return false;
        if (distanceY > boxHeight / 2 + radius)
            return false;

        if (distanceX <= boxWidth / 2)
            return true;
        if (distanceY <= boxHeight / 2)
            return true;

        float cornerDistanceSquared = ((distanceX - boxWidth / 2) * (distanceX - boxWidth / 2)) + ((distanceY - boxHeight / 2) * (distanceY - boxHeight / 2));
        return (cornerDistanceSquared <= (radius * radius));
    }
}
