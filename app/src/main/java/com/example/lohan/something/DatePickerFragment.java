package com.example.lohan.something;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by lohan on 11-10-2016.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    ChangeDateButton inter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        inter=(ChangeDateButton)context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //month:0-11
        String y=String.valueOf(year);
        String m=String.valueOf(month+1);
        String d=String.valueOf(day);
        String date=d+"/"+m+"/"+y;
        inter.changeDateButton(date);
    }
}
