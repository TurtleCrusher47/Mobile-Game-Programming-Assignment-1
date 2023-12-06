package com.example.mgp2023;

public interface ICollidableCircle
{
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(ICollidableCircle _other);
}

