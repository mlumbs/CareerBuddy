package com.qghoha.developerx.careerbuddy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import data.Data;
import data.JobContracts;
import global.MyApp;

public class Recommendations extends AppCompatActivity {
    public final static String reccommend_or_car = "com.qghoha.developerx.careerbuddy.Reco";
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String shared;
    String q;
    Data data;
    String value_key;
    boolean be_online;//tracks for changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Bundle extras = getIntent().getExtras();
        data =((MyApp)getApplication()).getData();
   String tempData;

        q="0";
          be_online=false;

        if (extras != null) {
            String value = extras.getString(reccommend_or_car);
            switch (Integer.parseInt(value)) {
                case 1:
                    shared= Data.C1;
                    be_online=data.C1()==data.A1();
                    //tempData=data.A1();
                    value_key=data.C1();
                    q="1";
                    break;
                case 2:
                    shared=Data.C2;
                    be_online=data.C2()==data.A2();
                    value_key=data.C2();

                    q="2";
                    break;
                case 3 :
                    shared=Data.C3;
                    be_online=data.C3()==data.A3();
                    value_key=data.C3();
                    q="3";
                    break;
            }
        }
        collapsingToolbar.setTitle(value_key);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();

            arguments.putParcelable(RecommendationsFragment.DETAIL_URI, JobContracts.Report_reco.BuildExpId(q));
            arguments.putBoolean(RecommendationsFragment.ONLINE_GO,be_online);
            arguments.putString(RecommendationsFragment.BEING_ONLINE,shared);
            arguments.putString(RecommendationsFragment.CHOICE_VALUE,value_key);
            RecommendationsFragment fragment = new RecommendationsFragment();
            fragment.setArguments(arguments);


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containe, fragment).commit();

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
