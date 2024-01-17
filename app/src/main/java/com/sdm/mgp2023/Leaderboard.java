package com.sdm.mgp2023;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.window.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

public class Leaderboard extends Activity  implements OnClickListener, StateBase
{
    //we have 2 buttons start button and back button

    //define buttons before start as buttons are objects (define as private as is solely used here)
    private Button btn_back;

    private TextView tv_score;
    private EditText ed_text;
    private Button bt_apply,bt_save;

    public static final String SHARED_PREFS = "SharePref";
    public static final String TEXT = "text";

    private String text;
    @Override
    protected void onCreate(Bundle SaveInstanceState)
    {
        super.onCreate(SaveInstanceState);
        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.leaderboardpage);

        btn_back=(Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        tv_score = (TextView)findViewById(R.id.tv_score);
//        ed_text=(EditText) findViewById(R.id.ed_text);
//        bt_apply = (Button) findViewById(R.id.btn_apply);
//        bt_save = (Button)findViewById(R.id.btn_save);

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

        if(v == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
            StateManager.Instance.ChangeState("MainMenu");

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
        return "Leaderboard";
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