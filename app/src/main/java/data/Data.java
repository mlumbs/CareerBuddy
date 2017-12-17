package data;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by DevelopX on 2016-08-01.
 */

public class Data {

    //Note that SharedPreferences are called after onCreate of an activity ,So the caller of this class must be aware of this


    Context context ;
    SharedPreferences sharedPref;// object points to a file containing key-value pairs and provides simple methods to read and write them
    public static int track_objects =0;
    SharedPreferences.Editor editor;


    private String TAG = Data.class.getSimpleName();
    public static final String LAST_ENTRY = "last_entry";
    public static final String PREF_FILE="com.qghoha.developerx.careerbuddy_key";
    public static final String RESULT_STATUS="report_status";
    public static final String POINTS_NAME="points";

    public static final String NAME="name";
    public static final String NAME_SCHOOL="name_shool";
    public static final String ID="id_number";
    //trakcs choice of careers
    public static final String C1="c1";
    public static final String C2="c2";
    public static final String C3="c3";


    //Stands for careers definition ,display them in the card_view
    public static final String D1="d1";
    public static final String D2="d2";
    public static final String D3="d3";

    //Comparing changes tracking,wheather we need to go online
    public static final String A1="a1";
    public static final String A2="a2";
    public static final String A3="a3";

     public Data(Context m){
           this.context=m;
           this.sharedPref =m.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
           this.editor =sharedPref.edit();
           track_objects++;
          }



    public String getName() {
        return sharedPref.getString(NAME,"");
    }

    public String getschool() {
        return sharedPref.getString(NAME_SCHOOL,"");
    }
    public String getID() {
        return sharedPref.getString(ID,"");
    }

    public void setName(String name)
    {
        editor.putString(NAME, name);
        editor.commit();
    }

    public void setSchool(String name)
    {
        editor.putString(NAME_SCHOOL, name);
        editor.commit();
    }
    public void setID(String name)
    {
        editor.putString(ID, name);
        editor.commit();
    }


    public void SetStatusResults(boolean name)
    {
        editor.putBoolean(RESULT_STATUS, name);
        editor.commit();
    }

    public Boolean getStatus() {
        return sharedPref.getBoolean(RESULT_STATUS,false);
    }

    public String C1() {
        return sharedPref.getString(C1,"");
    }

    public String C2() {
        return sharedPref.getString(C2,"");
    }

    public String C3() {
        return sharedPref.getString(C3,"");
    }

    //Definition
    public String D1() {
        return sharedPref.getString(D1,"");
    }

    public String D2() {
        return sharedPref.getString(D2,"");
    }

    public String D3() {
        return sharedPref.getString(D3,"");
    }

    public void setA1(String a) {
        editor.putString(A1, a);
        editor.commit();
    }

    public void setA2(String a) {
        editor.putString(A2, a);
        editor.commit();
    }

    public void setA3(String a) {
        editor.putString(A3, a);
        editor.commit();
    }
//Comparing
    public String A1() {
        return sharedPref.getString(A1,"");
    }

    public String A2() {
        return sharedPref.getString(A2,"");
    }

    public String A3() {
        return sharedPref.getString(A3,"");
    }



    public void SetPoints(boolean name)
    {
        editor.putBoolean(POINTS_NAME, name);
        editor.commit();
    }

    public Boolean getPoints() {
        return sharedPref.getBoolean(POINTS_NAME,false);
    }


    public class LAST_ENTRY {

    }

}
