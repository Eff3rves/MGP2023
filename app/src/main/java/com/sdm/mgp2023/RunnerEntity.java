package com.sdm.mgp2023;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.VibrationEffect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import java.util.Random;

import android.os.Vibrator;

public class RunnerEntity implements EntityBase,Collidable{
    // 1. Declare the use of spritesheet using Sprite class.
    private  Sprite spritesheet = null;
    private boolean isDone = false;
    private boolean isInit = false;

    private boolean isCollidingWithGround = false;
    public boolean isGrav = true;
    private float gravity = 9800;

    // Variables to be used or can be used.
    public float xPos, yPos, xDir, yDir, lifeTime;

    // For use with the TouchManager.class
    private boolean hasTouched = false;

    int ScreenWidth, ScreenHeight;

    private Vibrator _vibrator;

    public static RunnerEntity Create() {
        RunnerEntity result = new RunnerEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_RUNNER);
        return result;
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
        // 2. Loading spritesheet
        spritesheet = new Sprite(Bitmap.createScaledBitmap(ResourceManager.Instance.GetBitmap(R.drawable.nightborne),2000,500,true), 1,6,12);
        //smurf uis 4 rows 4 columns and 16frames

        //in the sprite class, there is SetAnimationFrames
        //spritesheet.SetAnimationFrames(1,8);

        // 3. Get some random position of x and y
        //just random ways to but could have diff ways to move or interact with the char

        Random ranGen = new Random();

        xPos = PlayerStats.Instance.getPlayerStartPosX();

        yPos = PlayerStats.Instance.getPlayerStartPosY();

        xDir = ranGen.nextFloat()*100.0f-50.0f;
        yDir = ranGen.nextFloat()*100.0f-50.0f;

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        isInit = true;

        _vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;

        if(PlayerStats.Instance.getPlayerHp() <=0){
            if(LoseDialogFragment.IsShown){
                return;
            }
            LoseDialogFragment newLose = new LoseDialogFragment();
            newLose.show(GamePage.Instance.getSupportFragmentManager(),"Lose");
        }


        // 4. Update spritesheet
        spritesheet.Update(_dt);
        // 5. Deal with the touch on screen for interaction of the image using collision check
        //temp since i think i will change this to a jump
//        if (TouchManager.Instance.HasTouch())
//        {
//            // 6. Check collision here!!!
//            float imgRadius = spritesheet.GetWidth()*0.5f;
//
//
//
//            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f,xPos,yPos,imgRadius)||hasTouched)
//            {
//                // Collided!
//                hasTouched = true;
//
//                // 7. Drag the sprite around the screen
//                xPos = TouchManager.Instance.GetPosX();
//                yPos = TouchManager.Instance.GetPosY();
//                xDir += xDir*_dt;
//                yDir += yDir*_dt;
//            }
//
//            return;
//        }

        // Inside RunnerEntity's Update method or elsewhere in your game logic
        if (isCollidingWithGround()) {
            // The runner is currently colliding with the ground

        } else {
            // The runner is not colliding with the ground
            isGrav = true;

        }

        if(yPos >= ScreenHeight){
            PlayerStats.Instance.setPlayerHp(0);
        }

        if(PlayerStats.Instance.getJump()){
            applyJump();
        }

        applyGravity(_dt);
    }

    public  void startVibrate(){
        if(Build.VERSION.SDK_INT >= 26){
            _vibrator.vibrate(VibrationEffect.createOneShot(150,10));
        }
        else{
            long pattern[] = {0,50,0};
            _vibrator.vibrate(pattern,-1);
        }
    }

    public  void  stopVibrate(){
        _vibrator.cancel();
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
    public int GetRenderLayer() {
        return LayerConstants.SMURF_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_RUNNER;
    }

    @Override
    public void SetPosY(float move) {

    }

    @Override
    public void SetPosX(float move) {

    }

    @Override
    public String GetType() {
        return "RunnerEntity";
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
        return 250 * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType()!= this.GetType() && _other.GetType()=="CoinEntity"){
            startVibrate();
            Log.v(TAG,"Hit");
            PlayerStats.Instance.setPlayerScore(PlayerStats.Instance.getPlayerScore()+1);
        }
        // Check if the runner gets hit by the ground
        if (_other.GetType().equals("GroundEntity")) {
            GroundEntity ground = (GroundEntity) _other;

            // Compare positions to determine the direction of the hit
            if (GetPosY() < ground.GetPosY()+ground.GetScaleY()) {
                // Runner hits the ground from the top
                // Handle top collision here
                isCollidingWithGround = true;
            } else if (GetPosY() > ground.GetPosY()+ground.GetScaleY()) {
                // Runner hits the ground from the bottom
                // Handle bottom collision here
                isCollidingWithGround = true;
            } else {
                // Runner is no longer colliding with the ground
                isCollidingWithGround = false;
            }
        }
    }

    // Additional method to check if the runner is colliding with the ground
    public boolean isCollidingWithGround() {
        return isCollidingWithGround;
    }

    //apply gravity for jump
    // Function to apply gravity
    private void applyGravity(float dt) {
        if(isGrav){
            // Update the vertical position (yPos) based on gravity
            float deltaTimeSquared = dt * dt; // Square of time step

            yPos +=0.5f* gravity * deltaTimeSquared;
//        System.out.println(yPos);
        }


    }

    private void applyJump(){
        yPos-= 250;
        PlayerStats.Instance.setJumpFalse();
        //since at the start of the game gravity is already set as true, the only other time where gravity needs to be set true again is when the player jumps
        isGrav = true;
    }

}
