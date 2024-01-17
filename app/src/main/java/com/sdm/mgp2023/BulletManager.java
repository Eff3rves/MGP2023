package com.sdm.mgp2023;

import java.util.Random;

public class BulletManager {
    public final static BulletManager Instance = new BulletManager();

    private float timer = 0f;
    public void CreateBullets(){
        Random rand = new Random();
        int maxSpawn = 3;
        BulletEntity bullet;

        bullet = BulletEntity.Create();
        bullet.Init(EntityManager.Instance.getView());

    }

    public void Update(float _dt){
        timer += _dt;
        if(timer > 3.f){
            CreateBullets();
            timer =0;
        }
    }

}
