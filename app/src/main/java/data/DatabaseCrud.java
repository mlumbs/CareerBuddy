package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.qghoha.developerx.careerbuddy.CareDatabase;

import Add.AddReport;

/**
 * Created by DevelopX on 2016-08-17.
 * All CRUD Functions will be called as the  Service Sequences
 */
public class DatabaseCrud {
    //Write the shared preference automatically thus not saving to the database!!
    //So we need only need Noti1,Noti2 and Delete entry Preferences(This is command to delete obsolete values or entries)
    //every time the the NotificationUtil is called the SharePreference values must be re-written just delete old text and give it new text
    //they write only once
    //the Database must not take action of saving when C and Po and EntryID are Empty
    //this means that NotificationUtils can be sent without the need of a Job Entry
    //Also if The NotificationUtils has been clicked or viewed it should be automatically be deleted
    //Thus the NotificationUtils only fires if and only if the notification is not empty
    //Therefore if the notification is empty it should not fire

    public static void ToAaddReportEntry(Context c, String A, String B,
                                             String C, String D, String E) {
        ContentValues JobValues = new ContentValues();
        Log.v("ins",A+"  "+B+" "+C+" "+D+"  ToAaddReportEntry");
        JobValues.put(JobContracts.ReportEntry.COLUMN_EA, A);
        JobValues.put(JobContracts.ReportEntry.COLUMN_EB, B);
        JobValues.put(JobContracts.ReportEntry.COLUMN_EC, C);
        JobValues.put(JobContracts.ReportEntry.COLUMN_ED, D);
        JobValues.put(JobContracts.ReportEntry.COLUMN_EE, E);
        c.getContentResolver().insert(JobContracts.ReportEntry.CONTENT_URI, JobValues);
        Log.v("Crud","entering on Crud");
    }


    public static void ToAllEntry(Context c, String A, String B,
                                         String C, String D, String E) {
        ContentValues JobValues = new ContentValues();
        //Log.v("CRUD",JobContracts.AllEntries.CONTENT_URI+"");
        Log.v("ins",A+"  "+D+" "+"ToAllEntry");
        JobValues.put(JobContracts.AllEntries.COLUMN_EA, A);
        JobValues.put(JobContracts.AllEntries.COLUMN_EB, B);
        JobValues.put(JobContracts.AllEntries.COLUMN_EC, C);
        JobValues.put(JobContracts.AllEntries.COLUMN_ED, D);
        JobValues.put(JobContracts.AllEntries.COLUMN_EE, E);

        c.getContentResolver().insert(JobContracts.AllEntries.CONTENT_URI, JobValues);
        //Log.v("Crud","entering on Crud");
    }


    /**
     *
     * @param c Context as general
     * @param id is used for further linking or information outside of this platform
     * @param category comes from the shared preference value in the choice
     * @param B is a report entry or reccomendations
     * @param C is accept or reject status
     * @param D School
     * @param E Degree
     * @param F points_required
     * @param G
     * @param H
     */
    public static void ToAddRecommendationsEntry(Context c, String id,String category, String B,
                                  String C, String D, String E,String F,String G,String H) {
        ContentValues JobValues = new ContentValues();
        JobValues.put(JobContracts.Report_reco.COLUMN_ENTRY_ID, id);
        JobValues.put(JobContracts.Report_reco.COLUMN_EA, category);//category of choice is stored in EA
        JobValues.put(JobContracts.Report_reco.COLUMN_EB, B); //Report of R1
        JobValues.put(JobContracts.Report_reco.COLUMN_EC, C);//Accept or reject
        JobValues.put(JobContracts.Report_reco.COLUMN_ED, D);//INST
        JobValues.put(JobContracts.Report_reco.COLUMN_EE, E);//DEgree
        JobValues.put(JobContracts.Report_reco.COLUMN_EF, F);
        JobValues.put(JobContracts.Report_reco.COLUMN_EG, G);
        JobValues.put(JobContracts.Report_reco.COLUMN_EH, H);
        c.getContentResolver().insert(JobContracts.Report_reco.CONTENT_URI, JobValues);

    }


    /**
     * Used for a new query to refresh the list
     * @param c Context
     * @param A Category of choice........see above
     */
    public static void ToDeleteRecomendation(Context c, String A) {
        Log.v("Core","Start to delete");
        String selection = JobContracts.Report_reco.COLUMN_EA + " LIKE ?";

        String[] selectionArgs = { String.valueOf(A) };
        c.getContentResolver().delete(JobContracts.Report_reco.CONTENT_URI,selection,selectionArgs);
    }


    /**
     *
     * @param c
     * @param uri
     * @return
     */

    public static String getSuggest(Context c,Uri uri){
        String query = uri.getLastPathSegment().toLowerCase();
        return String.valueOf(c.getContentResolver().query(uri,null,null,null,null));
    }


    public static void ToUpdateReportEntry(Context c, String id , String A, String B,
                                               String C, String D, String E) {

        String selection = JobContracts.ReportEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        ContentValues JobValues = new ContentValues();
        JobValues.put(JobContracts.AllEntries.COLUMN_EA, A);
        JobValues.put(JobContracts.AllEntries.COLUMN_EB, B);
        JobValues.put(JobContracts.AllEntries.COLUMN_EC, C);
        JobValues.put(JobContracts.AllEntries.COLUMN_ED, D);
        JobValues.put(JobContracts.AllEntries.COLUMN_EE, E);
        c.getContentResolver().update(JobContracts.AllEntries.BuildExpId(id),JobValues,selection,selectionArgs);

    }

    public static void ToUpdateAllEntry(Context c, String id ,
                                           String C,String D) {
        Log.v("ins",C+"  "+D+" "+"ToUpdateAllEntry");
        String selection = JobContracts.AllEntries._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        ContentValues JobValues = new ContentValues();
        JobValues.put(JobContracts.AllEntries.COLUMN_EC, C);
        JobValues.put(JobContracts.AllEntries.COLUMN_ED, D);
        c.getContentResolver().update(JobContracts.AllEntries.BuildExpId(id),JobValues,selection,selectionArgs);

    }


    public static void ToUpdate_che(Context c, String id ,
                                      String D) {
        Log.v("check is",D);
        String selection = JobContracts.AllEntries.COLUMN_EA + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        ContentValues JobValues = new ContentValues();
        JobValues.put(JobContracts.AllEntries.COLUMN_ED, D);
        c.getContentResolver().update(JobContracts.AllEntries.BuildExpId(id),JobValues,selection,selectionArgs);

    }

    public static int ToUpdate_sp(Context c, String id ,
                               String C) {
        String selection = JobContracts.AllEntries.COLUMN_EA + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        ContentValues JobValues = new ContentValues();
        JobValues.put(JobContracts.AllEntries.COLUMN_EC, C);
      return c.getContentResolver().update(JobContracts.AllEntries.BuildExpId(id),JobValues,selection,selectionArgs);
    }

    /*
    Auto delete funtions is not that specific or savvy ,That is the date in the device may be wrong resulting in all data deleted!
    this funtion is aborted as a primary operator on this deleting operation
    A solution to this is to depend on one singular date which will be fetched and put automatically on the shared preference value
    This Function will run if and only if the server issued such date that is if the sharedpreferences is not empty
    After this function this function sharedpreferences will be reset to no value or empyty value
    therefore Server loads data into the db by starting a service than it will call auto delete within the server seconadry Service
     */
    //Note This Method is invalid for the Use in this Project but it serves as an example to handle similar actions and events
    @Deprecated
    public void AutoDelete(Context c){
        c.getContentResolver().delete(JobContracts.ReportEntry.CONTENT_URI,
                JobContracts.ReportEntry.COLUMN_EE + " <= ?",
                new String[]{Long.toString(System.currentTimeMillis() / 1000L)});

    }

    //Write this to a SharedPreference as it will help globalize the updating history
    //returns the last updates
    //should be the last call of the call of service updates after the insert session
    //Note This Method is invalid for the Use in this Project but it serves as an example to handle similar actions and events
    @Deprecated
    public String last_entry_id(Context c){
        String l ;
        try {
            String sortOrder = JobContracts.ReportEntry.COLUMN_EC + " DESC";
            Cursor cursor= c.getContentResolver().query(JobContracts.ReportEntry.CONTENT_URI,null,null,null,sortOrder);
            if(cursor==null)
                return "-1";
                cursor.moveToFirst();
                l =cursor.getString(cursor.getColumnIndex(JobContracts.ReportEntry.COLUMN_EC));
                return l;

        }catch (Exception e){
            return "-200";
        }

    }



    public static void ToDeleteReportEntry(Context c, String A) {
        Log.v("Del",A+"");
        String selection = JobContracts.ReportEntry.COLUMN_EA + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(A) };
        c.getContentResolver().delete(JobContracts.ReportEntry.BuildExpId(A),selection,selectionArgs);
    }

    public static void ToDeleteAllEntry(Context c, String A) {
        String selection = JobContracts.ReportEntry._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(A) };
        c.getContentResolver().delete(JobContracts.AllEntries.BuildExpId(A),selection,selectionArgs);
    }




    public static void InitData(Context c){
        ToAllEntry(c,"DRA","Dramatic Arts","0","0","");
        ToAllEntry(c,"ECO","Economics","0","0","");
        ToAllEntry(c,"ETE","Electrical Technology","0","0","");
        ToAllEntry(c,"EGD","Engineering Graphics Design","0","0","");
        ToAllEntry(c,"GEO","Geography","0","0","");
        ToAllEntry(c,"HIS","History","0","0","");
        ToAllEntry(c,"HST","Hospitality Studies","0","0","");
        ToAllEntry(c,"ITE","Information Technology","0","0","");
        ToAllEntry(c,"LFS","Life Sciences","0","0","");
        ToAllEntry(c,"MET","Mechanical Technology","0","0","");
        ToAllEntry(c,"MSC","Music","0","0","");
        ToAllEntry(c,"PHY","Physical Science","0","0","");
        ToAllEntry(c,"RST","Religion Studies","0","0","");
        ToAllEntry(c,"VSA","Visual Arts","0","0","");
        ToAllEntry(c,"TRM","Tourism","0","0","");
        ToAllEntry(c,"MAT","Mathematics","0","0","");
        ToAllEntry(c,"MAL","Mathematical Literacy","0","0","");
        ToAllEntry(c,"LFO","Life Orientation","0","0","");
        ToAllEntry(c,"ACC","Accounting","0","0","");
        ToAllEntry(c,"AMP","Agricultural Management Practices","0","0","");
        ToAllEntry(c,"AGR","Agricultural Sciences","0","0","");
        ToAllEntry(c,"AGT","Agricultural Technology","0","0","");
        ToAllEntry(c,"BSS","Business Studies","0","0","");
        ToAllEntry(c,"CIT","Civil Technology","0","0","");
        ToAllEntry(c,"CAT","Computer Applications Technology","0","0","");
        ToAllEntry(c,"CST","Consumer Studies","0","0","");
        ToAllEntry(c,"DST","Dance Studies","0","0","");
        ToAllEntry(c,"DES","Design","0","0","");
        ToAllEntry(c,"HIN","Hindi","0","0","");
        ToAllEntry(c,"GUJ","Gujarati","0","0","");
        ToAllEntry(c,"TAM","Tamil","0","0","");
        ToAllEntry(c,"TEL","Telegu","0","0","");
        ToAllEntry(c,"ARA","Arabic","0","0","");
        ToAllEntry(c,"FRE","French","0","0","");
        ToAllEntry(c,"ITA","Italian","0","0","");
        ToAllEntry(c,"SPA","Spanish","0","0","");
        ToAllEntry(c,"MDG","Modern Greek","0","0","");
        ToAllEntry(c,"LAT","Latin","0","0","");
        ToAllEntry(c,"POR","Portuguese","0","0","");
        ToAllEntry(c,"GER","German","0","0","");
        ToAllEntry(c,"HEB","Hebrew","0","0","");
        ToAllEntry(c,"URB","Urdu","0","0","");
        ToAllEntry(c,"SER","Serbian","0","0","");
        ToAllEntry(c,"ENG","English","0","0","");
        ToAllEntry(c,"ISZ","isiZulu","0","0","");
        ToAllEntry(c,"ISX","isiXhosa","0","0","");
        ToAllEntry(c,"SIW","Siswati","0","0","");
        ToAllEntry(c,"SNB","isiNdebele","0","0","");
        ToAllEntry(c,"AFR","Afrikaans","5","0","");
        ToAllEntry(c,"SEP","Sepedi","0","0","");
        ToAllEntry(c,"SET","Sesotho","0","0","");
        ToAllEntry(c,"SEW","Setswana","0","0","");
        ToAllEntry(c,"XIT","Xitsonga","0","0","");
        ToAllEntry(c,"TSH","Tshivenda","0","0","");
        ToAllEntry(c,"NAS","Nautical Science","0","0","");
        ToAllEntry(c,"EQS","Equine Studies","0","0","");
        ToAllEntry(c,"SES","Sport and Exercise Science","0","0","");
        ToAllEntry(c,"MAE","Maritime Economics","0","0","");
    }


    public static void check_entryAll(Context c){
        //Cursor cursor;
        try {
            Log.v("Database Entered","IS CAUGHT");
//            String  sortOrder = JobContracts.AllEntries.COLUMN_EB + " DESC";
//           cursor =c.getContentResolver().query(JobContracts.AllEntries.CONTENT_URI,null,null,null,sortOrder);
//            if(cursor==null){
                InitData(c);
          //  }
            //cursor.close();
        }catch (Exception e){
            Log.v("Database Error",e.toString()+"IS CAUGHT");

        }

    }






















}
