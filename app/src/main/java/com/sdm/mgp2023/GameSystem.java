package com.sdm.mgp2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2023

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();
    public static final String SHARED_PREF_ID = "GameSaveFile";
    // Game stuff
    private boolean isPaused = false;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {
        //Get our shared preferences (Save file)
        sharedPref = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID,0);

        // 2. We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());

			// Please add state, NextPage.
        StateManager.Instance.AddState(new Nextpage());
			// Plese add state, MainGameSceneState.
        StateManager.Instance.AddState(new MainGameSceneState());

        StateManager.Instance.AddState(new Leaderboard());
    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

    public  void SaveEditBegin(){
        // Safety check, only allow if not already editing
        if(editor != null){
            return;
        }

        //Start the editing
        editor = sharedPref.edit();
    }

    public  void SaveEditEnd(){
        // Check if has editor
        if(editor == null){
            return;
        }

        editor.commit();
        editor = null; // Safety to ensure other functions will fail once commit done
    }

    public void SetIntInSave(String _key,int _value){
        if(editor == null)
            return;
        editor.putInt(_key,_value);
    }

    public int GetIntFromSave(String _key){
        return sharedPref.getInt(_key,10);
    }


}
