package com.qghoha.developerx.careerbuddy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import data.Data;

public class Search extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private ListView mListView;
    TextView editText;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String shared;
    String shared_definition;
    public final static String search_or_car = "com.qghoha.developerx.careerbuddy.KeySearch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.canShowOverflowMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        sharedPref = getSharedPreferences(
                Data.PREF_FILE, Context.MODE_PRIVATE);
        editor =sharedPref.edit();
       //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setTitle("");

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = (TextView) findViewById(R.id.text);
        mListView = (ListView) findViewById(R.id.list);
        handleIntent(getIntent());
        //onSearchRequested();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString(search_or_car);

            switch (Integer.parseInt(value)) {
                case 1:
                    shared=Data.C1;
                    shared_definition=Data.D1;
                    break;
                case 2:
                    shared=Data.C2;
                    shared_definition=Data.D2;
                    break;
                case 3 :
                    shared=Data.C3;
                    shared_definition=Data.D3;
                    break;

          }




        }




    }

    @Override
    protected void onNewIntent(Intent intent) {
        // Because this activity has set launchMode="singleTop", the system calls this method
        // to deliver the intent if this activity is currently the foreground activity when
        // invoked again (when the user executes a search from this activity, we don't create
        // a new instance of this activity, so the system delivers the search intent here)
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Cursor cursor = managedQuery(intent.getData(), null, null, null, null);
            if (cursor == null) {
                finish();
            }else {
                cursor.moveToFirst();

                TextView word = (TextView) findViewById(R.id.word);
                TextView definition = (TextView) findViewById(R.id.definition);

                int wIndex = cursor.getColumnIndexOrThrow(CareDatabase.KEY_CARE);
                int dIndex = cursor.getColumnIndexOrThrow(CareDatabase.KEY_DEFINITION);

                // word.setText(cursor.getString(wIndex));
                //definition.setText(cursor.getString(dIndex));
                //Log.v("Search", cursor.getString(dIndex));
                editor.putString(shared, cursor.getString(wIndex));
                editor.putString(shared_definition, cursor.getString(dIndex));
                editor.apply();
                finish();
            }

            Log.v("Search R",intent.getData().toString());
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // handles a search query
            String query = intent.getStringExtra(SearchManager.QUERY);
            showResults(query);
        }

    }

    private void showResults(String query) {
        Log.v("Search", "Entering search");

        Cursor cursor = managedQuery(CareProvider.CONTENT_URI, null, null,
                new String[]{query}, null);

        if (cursor == null) {
            // There are no results
            Log.v("Search", "no results");

            editText.setText(getString(R.string.no_results, new Object[]{query}));
        } else {
            // Display the number of results
            int count = cursor.getCount();
            Log.v("Search", "" + count);
            String countString = getResources().getQuantityString(R.plurals.search_results,
                    count, new Object[]{count, query});
            editText.setText(countString);

            // Specify the columns we want to display in the result
            String[] from = new String[]{CareDatabase.KEY_CARE,
                    CareDatabase.KEY_DEFINITION};

            // Specify the corresponding layout elements where we want the columns to go
            int[] to = new int[]{R.id.word,
                    R.id.definition};

            // Create a simple cursor adapter for the definitions and apply them to the ListView
            SimpleCursorAdapter words = new SimpleCursorAdapter(this,
                    R.layout.result, cursor, from, to);
            mListView.setAdapter(words);

            // Define the on-click listener for the list items
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Build the Intent used to open WordActivity with a specific word Uri
//                    Intent wordIntent = new Intent(getApplicationContext(), WordActivity.class);
//                    Uri data = Uri.withAppendedPath(CareProvider.CONTENT_URI,
//                            String.valueOf(id));
//                    wordIntent.setData(data);
//                    startActivity(wordIntent);
                   // Uri data = Uri.withAppendedPath(CareProvider.CONTENT_URI,
                           // String.valueOf(id));
                  // String y = String.valueOf(getContentResolver().query(data,null,null,null,null));
                    //editor.putString(shared, cursor.getString(dIndex));
                    //editor.apply();
                    //Log.v("clicked", "==>  "+y);
                    finish();
                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

     //not the default !

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) Search.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchItem.expandActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(Search.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //finish();
            super.onBackPressed();

    }








}
