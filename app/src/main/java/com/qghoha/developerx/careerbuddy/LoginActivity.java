package com.qghoha.developerx.careerbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import data.Data;
import global.MyApp;


public class LoginActivity extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 1;
    Data data;
    EditText name;
    EditText school;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        data =((MyApp)getApplication()).getData();

name= (EditText) findViewById(R.id.name);
        school= (EditText) findViewById(R.id.name_of_school);
        id= (EditText) findViewById(R.id.id);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_in);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(name.getText().toString()==" " || school.getText().toString()==" " ||id.getText().toString()==" "){
                    Snackbar.make(view, "Please details correctly", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{

                    data.setName(name.getText().toString());
                    data.setSchool(school.getText().toString());
                    data.setID(id.getText().toString());
                    launchAccountActivity();
                }




            }

        });





    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }

    private void launchAccountActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
