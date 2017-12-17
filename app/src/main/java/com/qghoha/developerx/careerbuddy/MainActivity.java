package com.qghoha.developerx.careerbuddy;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import data.Data;
import global.MyApp;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String rule ="1MAT5";
    String rule1 ="2MAT5-CAT6";
    Intent intent ;
    private ListView mListView;
    Data data;
    SearchView editsearch;
    Toolbar toolbar;
    TextView name;
    TextView shool;
    TextView id;
    public final static String Point_or_car = "com.qghoha.developerx.careerbuddy.Points";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data =((MyApp)getApplication()).getData();
        initializeScreen();

//        Button b =(Button) findViewById(R.id.points);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if( data.getStatus()){//Is the report filled ? If yes let go to recommendations and process
//                    intent = new Intent(getApplicationContext(),Recommendations.class);
//                    intent.putExtra(Point_or_car, 1);
//                    startActivity(intent);
//                }
//                else{ //If not entered Enter the Report
//                    intent = new Intent(getApplicationContext(), EnterResult.class);
//                    startActivity(intent);
//                }
//
//
//
//            }
//        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        name = (TextView) header.findViewById(R.id.name);
        shool = (TextView) header.findViewById(R.id.name_of_school);
        id = (TextView) header.findViewById(R.id.id);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_report) {
            intent = new Intent(getApplicationContext(), EnterResult.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);




        return true;
    }

    public int finds_method(String x){
        return Integer.parseInt(String.valueOf(x.charAt(0)));
    }

    public String extract_subject(String s){
        return s.substring(1,4);
    }

    public int extract_value(String s){
        return Integer.parseInt(String.valueOf(s.charAt(4)));
    }

   //either on the true
    public String rule_two(String sp){

String split[] =sp.split("-");


return split[0]+" "+split[1];
    }


    public void initializeScreen() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Create SectionPagerAdapter, set it as adapter to viewPager with setOffscreenPageLimit(2)
         **/
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        /**
         * Setup the mTabLayout with view pager
         */
        tabLayout.setupWithViewPager(viewPager);

    }



    public class SectionPagerAdapter extends FragmentStatePagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Use positions (0 and 1) to find and instantiate fragments with newInstance()
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            /**
             * Set fragment to different fragments depending on position in ViewPager
             */
            switch (position)
            {
                case 0:
                    fragment = new PresentChoices(); //This calls the Main
                    break;
                case 1:
                    fragment = new SearchPoints();
                    break;
                default:
                    //fragment = new CreateFragment();
                    break;
            }

            return fragment;
        }

        // TODO change this as the number of pages increase
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * Set string resources as titles for each fragment by it's position
         *
         * @param position
         */
        // TODO Use resource Strings
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.choice);
                case 1:
                    return getString(R.string.searchpoints);
                default:
                    return "JobList";
            }
        }


    }


}
