package com.sdm.mgp2023;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class LoseDialogFragment extends DialogFragment {
    //tp use this to check if the dialog button is pressed
    public  static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        IsShown = true;
        GameSystem.Instance.SetIsPaused(true);
        //use the Builder class to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You lose").setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                //cancel pause
                IsShown = false;
                StateManager.Instance.ChangeState("MainMenu");
            }
        });//.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //trigger pause
//                IsShown = false;
//                PlayerStats.Instance.resetPlayerHp();
//                PlayerStats.Instance.resetPlayerScore();
//            }
//        })
        //create the builder
        return  builder.create();
    }

    @Override
    public  void onCancel(DialogInterface dialog){

        IsShown = false;
    }

    @Override
    public  void onDismiss(DialogInterface dialog){
        IsShown = false;
    }
}
