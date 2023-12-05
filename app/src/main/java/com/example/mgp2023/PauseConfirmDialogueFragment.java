package com.example.mgp2023;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class PauseConfirmDialogueFragment extends DialogFragment {

    public static boolean isShown = false;

    //creates popup dialogue
    //dialogue will have 2 buttons
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){

        isShown = true;

        //use the builder class for creating the dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //the builder has different set functions, eg setIcon for images/sprites etc

        //below functions can also just be typed as
        //builder.setMessage();
        //builder.setPositiveButton();


        builder.setMessage("Confirm Pause")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //define what will occur when positive button is pressed
                        //to pause when pressed YES

                        //game system has existing methods to get paused boolean
                        //in the entity, the update method will pause or unpause the game
                        GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //define what will occur when positive button is pressed
                        //cancel pause
                    }
                });

        //Create our customised Dialog popup
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog){
        isShown = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        isShown = false;
    }
}
