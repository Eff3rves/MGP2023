package com.sdm.mgp2023;

import android.content.Context;
import android.graphics.Canvas;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 5.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // 3. Create Background 
        RenderBackground.Create();
        // Example to include another Renderview for Pause Button
        RunnerEntity.Create();
        RenderTextEntity.Create();
        PauseButtonEntity.Create();
//        CoinEntity.Create();
    }

    @Override
    public void OnExit() {
        // 4. Clear any instance instantiated via EntityManager.
        EntityManager.Instance.Clean();

        // 5. Clear or end any instance instantiated via GamePage.
        GamePage.Instance.finish();
       
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {
        timer += _dt;
        EntityManager.Instance.Update(_dt);
        if(timer > 5){
            CoinManager.Instance.CreateCoins();
            timer =0;
        }
    }
}



