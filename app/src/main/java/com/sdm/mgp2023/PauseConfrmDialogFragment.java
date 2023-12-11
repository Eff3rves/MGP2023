package com.sdm.mgp2023;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
public class PauseConfrmDialogFragment extends DialogFragment{
    //tp use this to check if the dialog button is pressed
    public  static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        IsShown = true;
        //use the Builder class to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause Game?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //trigger pause
                GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                IsShown = false;
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameSystem.Instance.SetIsPaused(GameSystem.Instance.GetIsPaused());
                //cancel pause
                IsShown = false;
            }
        });
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
