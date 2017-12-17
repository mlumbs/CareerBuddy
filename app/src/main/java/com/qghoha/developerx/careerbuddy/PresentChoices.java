package com.qghoha.developerx.careerbuddy;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import data.Data;
import global.MyApp;


/**
 * A simple {@link Fragment} subclass.
 */
public class PresentChoices extends Fragment {
    TextView editText1;
    TextView editText2;
    TextView editText3;
    Intent intent;
    Data data;

    public PresentChoices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data =((MyApp)getActivity().getApplication()).getData();
        View rootView= inflater.inflate(R.layout.fragment_present_choices, container, false);
        // Inflate the layout for this fragment
        editText1 = (TextView) rootView.findViewById(R.id.editText1);
        editText2 = (TextView) rootView.findViewById(R.id.editText2);
        editText3 = (TextView) rootView.findViewById(R.id.editText3);

        intent = new Intent(getActivity(),Search.class);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Search","clicked search");


                intent.putExtra(Search.search_or_car, "1");
                startActivity(intent);
            }
        });

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(Search.search_or_car, "2");
                startActivity(intent);
            }
        });


        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(Search.search_or_car, "3");
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(data.C1()==""||data.C2()==""||data.C3()==""){
                    Snackbar.make(view, "Please have 3 career choices", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    intent = new Intent(getActivity(), MyCareers.class);
                    startActivity(intent);
                }

//                if( data.getStatus()) {
//                    intent = new Intent(getApplicationContext(), MyCareers.class);
//                    startActivity(intent);
//                }else {
//                    intent = new Intent(getApplicationContext(), EnterResult.class);
//                    startActivity(intent);
//                }

            }


        });



      return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        editText1.setText(data.C1());
        editText2.setText(data.C2());
        editText3.setText(data.C3());
    }






}
