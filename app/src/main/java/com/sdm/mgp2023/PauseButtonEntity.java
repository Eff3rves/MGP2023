package com.sdm.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.net.PasswordAuthentication;

import kotlin.contracts.Returns;

public class PauseButtonEntity implements EntityBase{

    private boolean isDone = false;
    private boolean isInit = false;
    int ScreenWidth,ScreenHeight;
    private Bitmap bmP = null;

    private  Bitmap bmP2 = null;

    private Bitmap ScaledbmpP = null;

    private  Bitmap ScaledbmP2 = null;

    int xPos, yPos = 0;

    private  boolean Paused = false;

    private  float buttonDelay = 0;
    @Override
    public String GetType() {
        return null;
    }

    @Override
    public float GetRadius() {
        return 0;
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
        bmP = ResourceManager.Instance.GetBitmap(R.drawable.pause);
        bmP2 = ResourceManager.Instance.GetBitmap(R.drawable.pause1);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpP = Bitmap.createScaledBitmap(bmP,ScreenWidth/10,ScreenHeight/5,true);
        ScaledbmP2 = Bitmap.createScaledBitmap(bmP2,ScreenWidth/10,ScreenHeight/5,true);

        xPos = ScreenWidth -150;
        yPos = 150;

        isInit = true;

    }
    public static PauseButtonEntity Create() {
        PauseButtonEntity result = new PauseButtonEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }

    @Override
    public void Update(float _dt) {
        buttonDelay += _dt;

        if(TouchManager.Instance.HasTouch()){
            if(TouchManager.Instance.IsDown()&&!Paused){
                float imgRadius = ScaledbmpP.getHeight()*0.5f;
                if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius)&&buttonDelay>= 0.25f ){
                    Paused = true;

                    if(PauseConfrmDialogFragment.IsShown){
                        return;
                    }
                    PauseConfrmDialogFragment newPause = new PauseConfrmDialogFragment();
                    newPause.show(GamePage.Instance.getSupportFragmentManager(),"PauseConfirm");
                }
                buttonDelay =0;
            }
        }
        else Paused = false;
    }

    @Override
    public void Render(Canvas _canvas) {
        if(Paused == false)
            _canvas.drawBitmap(ScaledbmpP,xPos - ScaledbmpP.getWidth()*0.5f,yPos-ScaledbmpP.getHeight()*0.5f,null);
        else _canvas.drawBitmap(ScaledbmP2,xPos - ScaledbmP2.getWidth()*0.5f,yPos-ScaledbmP2.getHeight()*0.5f,null);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PAUSE_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PAUSE;
    }
}
