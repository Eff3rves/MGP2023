package com.sdm.mgp2023;

import java.util.Random;

public class CoinManager {
    public final static CoinManager Instance = new CoinManager();

    public void CreateCoins(){
        Random rand = new Random();
        int maxSpawn = 5;
        int spawnNo = rand.nextInt(maxSpawn);
        float coinYpos = 0;
        for(int i =0;i<spawnNo;i++){

            CoinEntity coin;

            coin = CoinEntity.Create();
            if(i == 0){
                coinYpos = coin.GetPosY();
            }
            coin.SetPosY(coinYpos);
            coin.SetPosX(coin.GetPosX()+ i*800);
        }
    }
}
