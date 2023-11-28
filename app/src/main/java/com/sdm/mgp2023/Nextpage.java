package com.sdm.mgp2023;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.widget.Button;

import java.io.Console;

public class Nextpage extends Activity implements OnClickListener, StateBase {

    private Button btn_back;
    @Override
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);

        setContentView(R.layout.nextpage);

        btn_back = (Button)findViewById(R.id.btn_back);

        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent();

        if (v == btn_back){
            intent.setClass(this, Mainmenu.class);
            StateManager.Instance.ChangeState("MainMenu");
        }

        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "NextPage";
    }
    @Override
    public void OnEnter(SurfaceView _view) {
        RenderBackground.Create();
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public void Update(float _dt) {

    }

    //Other 3 methods to be written here based on the Android activity lifecycle
    @Override
    protected void onPause(){super.onPause();}

    @Override
    protected void onStop(){super.onStop();}

    @Override
    protected void onDestroy(){super.onDestroy();}
}
