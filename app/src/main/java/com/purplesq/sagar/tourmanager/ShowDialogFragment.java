package com.purplesq.sagar.tourmanager;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by lenovo-pc on 24-Dec-15.
 */
public class ShowDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
    String title="Status";
        String message="Your message has been sent";
        builder.setTitle(title);
        builder.setMessage(message);
        String okbutton=getResources().getString(android.R.string.ok);
        builder.setPositiveButton(okbutton, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
               // Toast.makeText(getActivity(),"message sent",Toast.LENGTH_SHORT).show();

    }
}
