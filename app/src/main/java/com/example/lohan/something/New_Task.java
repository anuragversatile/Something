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

public class New_Task extends AppCompatActivity implements ChangeDateButton {
    private View view;
    private Switch aSwitch;
    EditText revise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__task);

        aSwitch=(Switch)findViewById(R.id.switch1);
        revise =(EditText)findViewById(R.id.revise);
        //attach a listener to check for changes in state
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    Toast.makeText(New_Task.this, "SWITCH ON, trivial tasks not repeated!!!", Toast.LENGTH_SHORT).show();
                    EditText edit=(EditText)findViewById(R.id.note);
                    revise.setVisibility(View.GONE);
                }else{
                    revise.setVisibility(View.VISIBLE);
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
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void changeDateButton(String date) {
        if(view==findViewById(R.id.startDate)){
            Button button=(Button)findViewById(R.id.startDate);
            button.setText(date);
        }
        else {
            Button button = (Button) findViewById(R.id.endDate);
            button.setText(date);
        }
    }

}
