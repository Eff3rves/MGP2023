package com.sdm.mgp2023;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class FloorManager {
    public final static FloorManager Instance = new FloorManager();

    public LinkedList<GroundEntity> groundEntities;

    private FloorManager() {
        // Initialize the linked list in the constructor
        groundEntities = new LinkedList<>();
    }


    public void CreateFloor() {
        GroundEntity floor;
        Random rand = new Random();

        // Create the first floor
        if (groundEntities.isEmpty()) {
            floor = GroundEntity.Create();
            floor.scaleX = rand.nextInt(1000);
            floor.scaleY = 100;
            floor.initBitmap();
            floor.SetDefaultPos(EntityManager.Instance.getView());
            floor.Init(EntityManager.Instance.getView());
            groundEntities.add(floor);
        } else {
            // Create subsequent floors with a maximum distance of 500
            GroundEntity lastFloor = groundEntities.getLast();
            float distance = rand.nextInt(300) + lastFloor.GetPosX() + lastFloor.GetScaleX();

            floor = GroundEntity.Create();
            floor.scaleX = Math.max(500, Math.min(1000, rand.nextInt(1000))); // Limit scaleX to the range [500, 1000]
            floor.scaleY = 100;
            floor.initBitmap();
            floor.SetDefaultPos(EntityManager.Instance.getView());
            floor.Init(EntityManager.Instance.getView());

            // Adjust the position based on the previous floor's position and scale
            float newXPos = lastFloor.GetPosX() + lastFloor.GetScaleX() + distance;
            floor.SetPosX(newXPos);

            groundEntities.add(floor);
        }

    }

    public void RemoveDoneFloors() {
        Iterator<GroundEntity> iterator = groundEntities.iterator();

        while (iterator.hasNext()) {
            GroundEntity ground = iterator.next();

            if (ground.IsDone()) {
                iterator.remove();
            }
        }
    }
    //System.out.println(coinYpos);
}
