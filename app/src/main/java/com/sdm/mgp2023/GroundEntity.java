package com.sdm.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class GroundEntity implements EntityBase,Collidable {

    private  Sprite spritesheet = null;
    private float posX,posY;
    float xDir;

    private boolean isDone = false;

    private boolean isInit = false;
    private float scaleX,scaleY;

    @Override
    public String GetType() {
        return "GroundEntity";
    }

    @Override
    public float GetPosX() {
        return posX;
    }

    @Override
    public float GetPosY() {
        return posY;
    }

    public float GetScaleY() {
        return scaleY;
    }

    public float GetScaleX() {
        return scaleX;
    }


    @Override
    public void SetPosY(float move) {

    }

    @Override
    public void SetPosX(float move) {

    }

    @Override
    public float GetRadius() {
        return scaleX;
    }

    @Override
    public void OnHit(Collidable _other) {
        //if the runner gets hit by the wall
        if(_other.GetType()=="RunnerEntity"){
            RunnerEntity runner = (RunnerEntity) _other;

            // Compare positions to determine the direction of the hit
            if (runner.GetPosY() < posY) {
                // Runner hits the ground from the top
                // Handle top collision here

                //set grav as false so that the player stops falling
                runner.isGrav= false;
            } else if (runner.GetPosY() > posY) {
                // Runner hits the ground from the bottom
                // Handle bottom collision here
            }

            if (runner.GetPosX() < posX) {
                // Runner hits the ground from the left
                // Handle left collision here
            } else if (runner.GetPosX() > posX) {
                // Runner hits the ground from the right
                // Handle right collision here


            }
        }
    }

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        spritesheet = new Sprite(Bitmap.createScaledBitmap(ResourceManager.Instance.GetBitmap(R.drawable.floor),100,100,true)
                , 1,1,1);

        Random ranGen = new Random();

        posX = _view.getWidth();

        posY = (float) _view.getHeight() /2 + ranGen.nextFloat()*(_view.getHeight()*0.75f);
        //System.out.println(yPos);
        xDir = -200.f;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;

        if(posX+scaleX < 0){
            isDone = true;
        }
        // 4. Update spritesheet
        spritesheet.Update(_dt);
        posX += xDir*_dt;


    }

    @Override
    public void Render(Canvas _canvas) {
        if(spritesheet != null){
            spritesheet.Render(_canvas, (int)posX, (int)posY);
        }
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.SMURF_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_FLOOR;
    }
}
