package com.sdm.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class BulletEntity implements EntityBase,Collidable {

    private  Sprite spritesheet = null;
    private boolean isDone = false;
    private Bitmap bmP = null;
    private boolean isInit = false;
    public float xPos, yPos, xDir, yDir, lifeTime;
    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public void SetPosY(float move){
        yPos = move;
    }

    @Override
    public void SetPosX(float move) {
        xPos = move;
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType() == "RunnerEntity"){
            PlayerStats.Instance.setPlayerHp(PlayerStats.Instance.getPlayerHp()-1);
            isDone = true;
        }
    }

    @Override
    public String GetType() {
        return "BulletEntity";
    }

    @Override
    public float GetRadius() {
        return 5;
    }

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }
    public static BulletEntity Create() {
        BulletEntity result = new BulletEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BULLET);
        return result;
    }
    @Override
    public void Init(SurfaceView _view) {
        // 2. Loading spritesheet

        spritesheet = new Sprite(Bitmap.createScaledBitmap(ResourceManager.Instance.GetBitmap(R.drawable.malware),100,100,true)
                , 1,1,1);

        //smurf uis 4 rows 4 columns and 16frames
        //in the sprite class, there is SetAnimationFrames
        //spritesheet.SetAnimationFrames(1,8);

        // 3. Get some random position of x and y
        //just random ways to but could have diff ways to move or interact with the char

        Random ranGen = new Random();

        xPos = _view.getWidth();

        yPos = ranGen.nextFloat()*(_view.getHeight()*0.75f);
        //System.out.println(yPos);
        xDir = -200.f;
        yDir = 10.f;

        isInit = true;

    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;
        // 4. Update spritesheet
        spritesheet.Update(_dt);

        if(xPos+50 < 0){
            isDone = true;
        }
        xPos += xDir*_dt;
    }

    @Override
    public void Render(Canvas _canvas) {
        // This is for our sprite animation!
        if(spritesheet != null){
            spritesheet.Render(_canvas, (int)xPos, (int)yPos);
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
    public EntityBase.ENTITY_TYPE GetEntityType() {
        return EntityBase.ENTITY_TYPE.ENT_BULLET;
    }
}
