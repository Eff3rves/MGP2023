package com.sdm.mgp2023;

// Created by TanSiewLan2021

public class Collision {

    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }

    public static boolean AABBCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        // Calculate the minimum and maximum coordinates of the two AABBs
        float minX1 = x1 - width1 / 2;
        float maxX1 = x1 + width1 / 2;
        float minY1 = y1 - height1 / 2;
        float maxY1 = y1 + height1 / 2;

        float minX2 = x2 - width2 / 2;
        float maxX2 = x2 + width2 / 2;
        float minY2 = y2 - height2 / 2;
        float maxY2 = y2 + height2 / 2;

        // Check for overlap on the X and Y axes
        boolean xOverlap = maxX1 >= minX2 && maxX2 >= minX1;
        boolean yOverlap = maxY1 >= minY2 && maxY2 >= minY1;

        // If there is overlap on both axes, there is a collision
        return xOverlap && yOverlap;
    }

}
