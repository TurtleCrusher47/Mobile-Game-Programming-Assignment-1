package com.example.mgp2023;

public interface ICollidableBox
{
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetWidth();
    float GetHeight();

    void OnHit(ICollidableBox _other);
}

