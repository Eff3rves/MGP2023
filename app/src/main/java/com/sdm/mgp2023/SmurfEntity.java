package com.sdm.mgp2023;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;
import java.util.Random;
import  android.os.Build;
import  android.os.VibrationEffect;
import android.os.Vibrator;

public class SmurfEntity implements EntityBase, Collidable{
    
	 // 1. Declare the use of spritesheet using Sprite class.
    private  Sprite spritesheet = null;
    private boolean isDone = false;
    private boolean isInit = false;

	 // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;
    
	 // For use with the TouchManager.class
    private boolean hasTouched = false;

    int ScreenWidth, ScreenHeight;

    private  Vibrator _vibrator;
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
        // New method using our own resource manager : Returns pre-loaded one if exists
        // 2. Loading spritesheet
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.smurf_sprite), 4,4,16);
	    //smurf uis 4 rows 4 columns and 16frames

        //in the sprite class, there is SetAnimationFrames
        //spritesheet.SetAnimationFrams(1,15);

        // 3. Get some random position of x and y
        //just random ways to but could have diff ways to move or interact with the char

        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();

        yPos = ranGen.nextFloat()*_view.getHeight();

        xDir = ranGen.nextFloat()*100.0f-50.0f;
        yDir = ranGen.nextFloat()*100.0f-50.0f;

        isInit = true;

        _vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
    }

//    public  void startVibrate(){
//        if(Build.VERSION.SDK_INT >= 26){
//            _vibrator.vibrate(VibrationEffect.createOneShot(150,10));
//        }
//        else{
//            long pattern[] = {0,50,0};
//            _vibrator.vibrate(pattern,-1);
//        }
//    }
//
//    public  void  stopVibrate(){
//        _vibrator.cancel();
//    }

    @Override
    public void Update(float _dt) {
        
		  // 4. Update spritesheet
        spritesheet.Update(_dt);
        // 5. Deal with the touch on screen for interaction of the image using collision check
        if (TouchManager.Instance.HasTouch())
        {
            // 6. Check collision here!!!
            float imgRadius = spritesheet.GetWidth()*0.5f;

            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f,xPos,yPos,imgRadius)||hasTouched)
            {
                // Collided!
                hasTouched = true;

                // 7. Drag the sprite around the screen
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();
                xDir += xDir*_dt;
                yDir += yDir*_dt;
            }




        }
    }



    @Override
    public void Render(Canvas _canvas) {
       
        // This is for our sprite animation!
        spritesheet.Render(_canvas, (int)xPos, (int)yPos);

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.SMURF_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static SmurfEntity Create()
    {
        SmurfEntity result = new SmurfEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_SMURF;}

    @Override
    public void SetPosY(float move) {

    }

    @Override
    public void SetPosX(float move) {

    }

    @Override
    public String GetType() {
        return "SmurfEntity";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return spritesheet.GetHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType()!= this.GetType() && _other.GetType()!="SmurfEntity"){
            Log.v(TAG,"Hit");
        }
//        if (_other.GetType() == "StarEntity") //Another Entity
//        {
//            SetIsDone(true);
//            Play an audio
//        }
    }

}
