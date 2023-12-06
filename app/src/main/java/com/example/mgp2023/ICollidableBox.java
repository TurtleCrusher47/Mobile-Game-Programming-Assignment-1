package com.example.mgp2023;

public interface ICollidableBox
{
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(ICollidableBox _other);
}

