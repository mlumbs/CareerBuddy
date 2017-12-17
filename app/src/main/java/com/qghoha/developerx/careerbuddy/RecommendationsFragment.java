package com.qghoha.developerx.careerbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import Adapters.Recommendations;
import data.Data;
import data.DatabaseCrud;
import data.JobContracts;
import global.MyApp;
import service.LoadService;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecommendationsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    Data data;
    String real;
    String change;
    static final String DETAIL_URI = "com.qghoha.developerx.careerbuddy.URI";
    static final String ONLINE_GO = "com.qghoha.developerx.careerbuddy.ONLINE_GO";
    static final String BEING_ONLINE = "com.qghoha.developerx.careerbuddy.BEING_ONLINE";
    static final String CHOICE_VALUE="com.qghoha.developerx.careerbuddy.BEING_VALUE";
    private boolean be_online;
    private String being_online;
    private String being_online_local_key;
    private String being_online_local_key_value;

    private static final int DETAIL_LOADER = 0;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    public static final String[] JOB_COLUMNS = {
            JobContracts.Report_reco._ID ,
            JobContracts.Report_reco.COLUMN_ENTRY_ID ,
            JobContracts.Report_reco.COLUMN_EA,
            JobContracts.Report_reco.COLUMN_EB ,
            JobContracts.Report_reco.COLUMN_EC,
            JobContracts.Report_reco.COLUMN_ED,
            JobContracts.Report_reco.COLUMN_EE,
            JobContracts.Report_reco.COLUMN_EF,
            JobContracts.Report_reco.COLUMN_EG,
            JobContracts.Report_reco.COLUMN_EH,
    };

    public static final int COL_JOB = 0;
    public static final int COL_ENTRY = 1;
    public static final int COL_A= 2; //category
    public static final int COL_B=3;//report;
    public  static final int COL_C= 4;//accept or reject
    public static final int COL_D= 5; //institution
    public  static final int COL_E = 6;
    public static final int COL_F = 7;
    public static final int COL_G= 8;
    public static final int COL_H= 9;



    private RecyclerView mRecyclerView;
    private Recommendations mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Uri mUri;


    public RecommendationsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data =((MyApp)getActivity().getApplication()).getData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(RecommendationsFragment.DETAIL_URI);
            Log.v("Reco",""+mUri);
            be_online=arguments.getBoolean(RecommendationsFragment.ONLINE_GO);
            Log.v("Reco",""+be_online);
            being_online=arguments.getString(RecommendationsFragment.BEING_ONLINE);//this value will be c1 or c2 or c3
            being_online_local_key_value=arguments.getString(RecommendationsFragment.CHOICE_VALUE);
        }
        new ReadServer().execute("http://melanicapital.jobl.co.za/Buddy/contentfeeds.php?q="+being_online);
       // being_online_local_key="";

        sharedPref = getActivity().getSharedPreferences(
                Data.PREF_FILE, Context.MODE_PRIVATE);
        editor =sharedPref.edit();
        View rootView = inflater.inflate(R.layout.fragment_recommendations, container, false);
        mRecyclerView= (RecyclerView)rootView.findViewById(R.id.lrecycler_view);
//        mRegistrationProgressBar = (ProgressBar) getActivity().findViewById(R
//                .id.registrationProgressBar);

       // mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        //mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // mRecyclerView.addItemDecoration(new SimpleDivider(getActivity()));
        //  Typeface p=Helper.mFont(3,getActivity());
        mAdapter = new Recommendations(getContext());

        mRecyclerView.setAdapter(mAdapter);

        switch (being_online) {
            case Data.C1://c1
                being_online_local_key=Data.A1;
               // data.setA1(being_online_local_key);
                break;
            case Data.C2://c2
                //data.setA2(being_online_local_key);
               being_online_local_key=Data.A2;
                break;
            case Data.C3: //3
               // data.setA3(being_online_local_key);
                being_online_local_key=Data.A3;
                break;
        }



        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if ( null != mUri ) {
            //   Log.v("Job onCreateLoader", "mUri is"+mUri);
            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    JOB_COLUMNS,
                    null,
                    null,
                    null
            );

        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }





    public class ReadServer extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {
            String fileUrl = params[0];
            Log.v("Core","Calling "+params[0]);
            try{
                if(!be_online){
                    //clear the databse first
                    DatabaseCrud.ToDeleteRecomendation(getContext(),JobContracts.Report_reco.getCategory(mUri));
                return loadJsonFromNetwork(fileUrl);
                }
            } catch (SocketTimeoutException e){
                Log.v("Core","Time out");
            } catch (IOException e){
                Log.v("Core","Time out");
            }
            //downloadFile(fileUrl, pdfFile);

            return null;
        }

        // This is called when doInBackground() is finished
        protected void onPostExecute(String result) {

            //Check if first the sharedPreference has been changed before loading a nee content

                LoadService.startActionRECOMMENT(getActivity(),result,JobContracts.Report_reco.getCategory(mUri));
           // editor.putString(being_online_local_key,being_online_local_key_value);
           // editor.putString(being_online_local_key,being_online_local_key_value);
            editor.putString(being_online_local_key,being_online_local_key_value);
            editor.apply();

            Log.v("Service stated"," ");

        }



    }



    private String loadJsonFromNetwork(String urlString) throws IOException {
        Log.v("Core",urlString);
        URL url =new URL(urlString);
        String JsonString =null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();


            if (inputStream == null) {
                // Nothing to do.
                JsonString = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
                // Log.v("Core","Buffer "+buffer);
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                Log.v("Core","Buffer length is 0 ");
                JsonString = null;
            }
            JsonString = buffer.toString();


        }catch (IOException e){
            e.getMessage();
            Log.v("Core",e.getMessage());
        }finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        Log.v("Core","Returning "+JsonString);
        return JsonString;//returns JSON
    }



}
