package global;

import android.app.Application;
import android.content.Context;

import data.Data;

import static data.DatabaseCrud.check_entryAll;

/**
 * Created by DevelopX on 2016-03-14.
 */
public class MyApp extends Application {


    public Data mdata;


    public void Initialize(){

        if(mdata==null){
           mdata =new Data(getApplicationContext());
            //check_entryAll(getApplicationContext());
        }

    }


    public Data getData(){
        Initialize(); //Make sure the it exists

        return mdata; //Return the data
    }



}
