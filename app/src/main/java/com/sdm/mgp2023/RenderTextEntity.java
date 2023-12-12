package com.sdm.mgp2023;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class RenderTextEntity implements EntityBase{

    //paint Obj - helps to define color, size and varies used of the type.
    Paint paint = new Paint();

    // Color - RGB we are going to set 3 variable names to Read Green and Blue, so that we can use range of numbers from 0 to 255 to play around..
    // If you create a random numbers from 0 - 255 to be using theses 3 RGB variables, you can get raibow , Disco text

    private  int red = 255, green = 255, blue = 255;
    private boolean isDone = false;
    private boolean isInit = false;

    //we want to draw text/ have a text that shows the framerate of the build running. FPS:60

    int frameCount;
    long lastTime =0;
    long lastFPSTime= 0;
    float fps;

    //define a name to the font obj
    protected Typeface myfont;

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
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "Beyonders.otf");

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        //get the framerate fps number
        long currenttime = System.currentTimeMillis();
        long lastTime = currenttime;
        if(currenttime - lastFPSTime>1000){
            fps = (frameCount*1000)/(currenttime-lastFPSTime);
            lastFPSTime = currenttime;
            frameCount =0;
        }
        frameCount++;
    }

    @Override
    public void Render(Canvas _canvas) {
        paint.setARGB(255,red,green,blue); //text will be opaque and white
        paint.setTypeface(myfont);
        paint.setTextSize(50);

        _canvas.drawText("FPS "+fps,30,80,paint);
        _canvas.drawText("Score: "+PlayerStats.Instance.getPlayerScore(),30,160,paint);
    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    public static  RenderTextEntity Create(){
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return  result;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

}
