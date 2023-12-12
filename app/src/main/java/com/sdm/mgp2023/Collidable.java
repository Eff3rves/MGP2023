package com.sdm.mgp2023;

// Created by TanSiewLan2021

public interface Collidable {
    String GetType();

    float GetPosX();
    float GetPosY();

    void SetPosY(float move);
    void SetPosX(float move);
    float GetRadius();

    void OnHit(Collidable _other);
}

