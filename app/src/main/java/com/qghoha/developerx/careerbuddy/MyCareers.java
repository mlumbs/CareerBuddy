package com.qghoha.developerx.careerbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import data.Data;
import global.MyApp;

import static com.qghoha.developerx.careerbuddy.R.id.cardOne;
import static com.qghoha.developerx.careerbuddy.R.id.cardThree;
import static com.qghoha.developerx.careerbuddy.R.id.cardTwo;
import static com.qghoha.developerx.careerbuddy.Recommendations.reccommend_or_car;





public class MyCareers extends AppCompatActivity {
    Intent intent ;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_careers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data =((MyApp)getApplication()).getData();

        TextView ct1= (TextView) findViewById(R.id.info_texta);
        TextView cta1= (TextView) findViewById(R.id.info_texta_etc);
        TextView ct2= (TextView) findViewById(R.id.info_textb);
        TextView ctb2= (TextView) findViewById(R.id.info_textb_etc);
        TextView ct3= (TextView) findViewById(R.id.info_textc);
        TextView ctc3= (TextView) findViewById(R.id.info_textc_etc);

        ct1.setText(data.C1());
        cta1.setText(data.D1());

        ct2.setText(data.C2());
        ctb2.setText(data.D2());

        ct3.setText(data.C3());
        ctc3.setText(data.D3());



        CardView c1 = (CardView) findViewById(cardOne);
        CardView c2 = (CardView) findViewById(cardTwo);
        CardView c3 = (CardView) findViewById(cardThree);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Recommendations.class);
               intent.putExtra(reccommend_or_car, "1");
                startActivity(intent);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Recommendations.class);
                 intent.putExtra(reccommend_or_car,"2");
                startActivity(intent);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Recommendations.class);
                intent.putExtra(reccommend_or_car,"3");
                startActivity(intent);
            }
        });






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
