package com.example.lohan.something;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lohan.something.ChoiceLists.GetChoices;
import com.example.lohan.something.ChoiceLists.SingleChoice;

public class New_DailyData extends AppCompatActivity implements ChangeDateButton, GetChoices {

    private static final String COMPLETELY_FULFILLED="Completely";
    private static final String PARTIALLY_FULFILLED="Partially";
    private static final String MOURNFUL_NUMBERS="Mournful Numbers";
    private String[] fulfilledItems={COMPLETELY_FULFILLED,PARTIALLY_FULFILLED,MOURNFUL_NUMBERS};
    private String date;
    private String[] tasksDone;
    private String fulfilled;
    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__daily_data);
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public void onSave(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        EditText notes=(EditText)findViewById(R.id.note);
        this.note=notes.getText().toString();

    }

    @Override
    public void changeDateButton(String date) {
        Button button=(Button)findViewById(R.id.dailyDate);
        button.setText(date);
        this.date=date;
    }
    public void onFulfilledClicked(View view){
        DialogFragment sc=new SingleChoice();
        Bundle bundle=new Bundle();
        bundle.putString("title","Fulfilled???");
        bundle.putStringArray("items",fulfilledItems);
        sc.setArguments(bundle);
        sc.show(getSupportFragmentManager(), "FulfilledDialogFragment");


    }

    @Override
    public void getChoices(String[] choices,String title) {
        if("Fulfilled???".equals(title)) {
            this.fulfilled = choices[0];
            Button b=(Button)findViewById(R.id.fulfilledButton);
            b.setText(fulfilled);
        }
    }
}
