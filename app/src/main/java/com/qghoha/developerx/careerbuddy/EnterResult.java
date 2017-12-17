package com.qghoha.developerx.careerbuddy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import Add.AddReport;
import data.Data;
import data.DatabaseCrud;
import global.MyApp;

public class EnterResult extends AppCompatActivity {
    Intent intent;
    public static final String LOG = EnterResult.class.getSimpleName();
    Data data;

    public final static String EXTRA_MESSAGE = "com.qghoha.developerx.careerbuddy.enter";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data =((MyApp)getApplication()).getData();
        FloatingActionButton fabeces = (FloatingActionButton) findViewById(R.id.fab);
        fabeces.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
        fabeces.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intent = new Intent(getApplicationContext(), EnterActivity.class);
                intent.putExtra(EXTRA_MESSAGE, 1);
                startActivity(intent);
               new ReadServer().execute();
//                Log.v(LOG,"pressed");
//                AddReport fragment = new AddReport();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment, fragment)
//                        .commit();



            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public class ReadServer extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... params) {
            data.setA1("");
            data.setA2("");
            data.setA3("");

          //  DatabaseCrud.ToAaddReportEntry(getApplicationContext(),"A","B","C","D","E");
         return null;
        }

    }



}


