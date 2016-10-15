package com.example.lohan.something.ChoiceLists;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;


/**
 * Created by lohan on 15-10-2016.
 */

public class SingleChoice extends DialogFragment {
    private String title;
    private String[] items;
    private GetChoices ch;
    private String[] choice=new String[1];

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ch=(GetChoices)context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        title=bundle.getString("title");
        items=bundle.getStringArray("items");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String sel=items[which];
                        choice[0]=sel;
                        ch.getChoices(choice,title);
                    }
                });
        return builder.create();
    }
}
