package com.sdm.mgp2023;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
import android.window.SplashScreen;

public class Mainmenu extends Activity implements OnClickListener, StateBase
{
    //we have 2 buttons start button and back button
    //start button when press will go to another page..maybe gamepage
    //back button when press will go back to splash page for now

    //define buttons before start as buttons are objects (define as private as is solely used here)
    private Button btn_start; //int a
    private Button btn_back;

    @Override
    protected void onCreate(Bundle SaveInstanceState)
    {
        super.onCreate(SaveInstanceState);

        setContentView(R.layout.mainmenu);

        //never import R ( very important ILLEGAL)
        //even if android tell u do dont do it
        //if see this means your XML file has error
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_back=(Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        //this allows the correct button to be the object name and
        //for this case button.
        //setonclicklistener to the specified button so that we know
        //when the specific button is clicked/touch
        //it knows what to do

    }
    @Override
    //invoke a callback method event in the view
    public void onClick(View v){
        //intent = action to be performed
        //Intent = is an object providing runtime binding
        //have to create new instance of object to use

        //we need to check if start or back button is clicked/touched on the screen
        //then after, decide what to do
        //if start button, will go to another page eg gamepage

        Intent intent = new Intent();
        //intent to move from mainmenu class to eg, gamepage class
        //for now we go to splashpage

        if(v== btn_start){
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("MainGame");
            //Goes to MainGameSceneState hence why need to set class to Game Page
        }
        else if(v == btn_back)
        {
            this.finishAffinity();
        }
        /*
        If you want to add an exit button on the main menu.

        else if (v == btn_quit){
            this.finishAffinity();
        }
         */
        startActivity(intent); //Here meant, start the next activity class it is indicated.
    }

    @Override
    public String GetName(){
        return "MainMenu";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {
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