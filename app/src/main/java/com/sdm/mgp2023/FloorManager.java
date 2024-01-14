package com.sdm.mgp2023;

import java.util.Random;

public class FloorManager {
    public final static FloorManager Instance = new FloorManager();

    public void CreateFloor(){

        GroundEntity floor;

        floor = GroundEntity.Create();
        floor.Init(EntityManager.Instance.getView());
        //System.out.println(coinYpos);

    }
}
