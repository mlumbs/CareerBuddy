package com.qghoha.developerx.careerbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import Add.AddReport;
import Edit.EditReport;
import data.Data;

import static data.DatabaseCrud.check_entryAll;

public class EnterActivity extends AppCompatActivity {
    private int store;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    int db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_cancel);
        sharedPref = getSharedPreferences(
                Data.PREF_FILE, Context.MODE_PRIVATE);
        db= Integer.parseInt(sharedPref.getString(Data.LAST_ENTRY,"0"));
        new  CheckDB().execute();
        editor =sharedPref.edit();
        editor.putString(Data.LAST_ENTRY, "1");
        editor.apply();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.coint, new AddReport())
                    .commit();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public class CheckDB extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            if(db==0) {
                check_entryAll(getApplicationContext());

            }
            return null;
        }

    }




}
