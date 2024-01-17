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
    public float scaleX,scaleY;

    public static GroundEntity Create() {
        GroundEntity result = new GroundEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_FLOOR);
        return result;
    }

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
        return scaleX*2;
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
            if (runner.GetPosY() < posY+scaleY) {
                // Runner hits the ground from the top
                // Handle top collision here

                //set grav as false so that the player stops falling

                if (runner.GetPosX() < posX + scaleX && runner.GetPosX() > posX - scaleX) {

                    runner.isGrav= false;


                }
            } else if (runner.GetPosY() > posY-scaleY) {
                // Runner hits the ground from the bottom
                // Handle bottom collision here
                runner.yPos = posY-scaleY;

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
        (scaleY) = (scaleX) = 100;


        Random ranGen = new Random();


        //System.out.println(yPos);
        xDir = -200.f;

        isInit = true;
    }

    public void SetDefaultPos(SurfaceView _view){
        Random ranGen = new Random();
        posX = _view.getWidth();

        posY = Math.min( (float)_view.getHeight() / 2 + ranGen.nextFloat() * (_view.getHeight() * 0.75f),_view.getHeight() - 100);

    }


    public void initBitmap(){
        spritesheet = new Sprite(Bitmap.createScaledBitmap(ResourceManager.Instance.GetBitmap(R.drawable.floor),(int)scaleX,(int)scaleY,true)
                , 1,1,1);
    }
    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;

        if(posX+scaleX*2 < 0){
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
        return LayerConstants.PLAYER_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_FLOOR;
    }
}
