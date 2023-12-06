package com.example.mgp2023;

public interface ICollidableSphere
{
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(ICollidableSphere _other);
}

