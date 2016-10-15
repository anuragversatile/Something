package com.example.lohan.something;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lohan.something.ChoiceLists.GetChoices;

public class New_Task extends AppCompatActivity implements ChangeDateButton, GetChoices {
    private View view;
    private Switch aSwitch;
    EditText reviseEditText;
    private String date;//creation date//TODO: take timestamp???
    private boolean trivial;
    private int parentID;//TODO: _ID can take which2 values???
    private boolean isParent;
    private String startDate, endDate;
    private String note;
    private int repeat,revise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__task);

        aSwitch=(Switch)findViewById(R.id.switch1);
        reviseEditText =(EditText)findViewById(R.id.revise);
        //attach a listener to check for changes in state
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    trivial=true;
                    reviseEditText.setVisibility(View.GONE);
                    Toast.makeText(New_Task.this, "SWITCH ON, trivial tasks not repeated!!!", Toast.LENGTH_SHORT).show();

                }else{
                    reviseEditText.setVisibility(View.VISIBLE);
                    trivial=false;
                    Toast.makeText(New_Task.this, "SWITCH OFF!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        view=v;

    }
    public void onSave(View v){
        EditText editText=(EditText)findViewById(R.id.note);
        this.note=editText.getText().toString();
        editText=(EditText)findViewById(R.id.repeat);
        if(editText.getText().toString().equals("")){
            this.repeat=0;
        }
        else {
            this.repeat = Integer.parseInt(editText.getText().toString());
        }
        if(trivial){//if it is trivial then donot revise
            this.revise=-999;

        }
        else{
            editText=(EditText)findViewById(R.id.revise);
            if(editText.getText().toString().equals("")){
                this.revise=0;
            }
            else {
                this.revise = Integer.parseInt(editText.getText().toString());
            }
        }
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void changeDateButton(String date) {
        if(view==findViewById(R.id.startDate)){
            Button button=(Button)findViewById(R.id.startDate);
            button.setText(date);
            this.startDate=date;
        }
        else {
            Button button = (Button) findViewById(R.id.endDate);
            button.setText(date);
            this.endDate=date;
        }
    }

    @Override
    public void getChoices(String[] choices, String title) {

    }
}
