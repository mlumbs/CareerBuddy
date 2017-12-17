package data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DevelopX on 2015-09-04.
 * Copying this file Data means that you have the right to use this private API
 * To use this file usefully change the package to the specified ,as in the Manifest
 * Edit it to your Rasons according to the API
 */

public class JobContracts {

    public static final String CONTENT_AUTHORITY="com.qghoha.developerx.careerbuddy";
    public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_REPORT="DataReport";
    public static final String PATH_RECCO="DataReccomendations";
    public static final String PATH_ALL="DataSubjetcs";
    public static final String Path_SUGGEST="search_suggest_query";


    public static final class ReportEntry implements BaseColumns {

        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(PATH_REPORT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REPORT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REPORT;

        public static final String TABLE_NAME ="Report";

        public static final String COLUMN_EA ="ea";
        public static final String COLUMN_EB ="eb";
        public static final String COLUMN_EC ="ec";
        public static final String COLUMN_ED ="ed";
        public static final String COLUMN_EE ="ee";

        public  static Uri buildJobUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri BuildExpId(String id){//Dont delete

            return CONTENT_URI.buildUpon().appendPath(id).build();
        }


        public static String getIDByFromUri(Uri uri){

            return uri.getPathSegments().get(1);
        }


    }

    public static final class Report_reco implements BaseColumns {

        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECCO).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECCO;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECCO;

        public static final String TABLE_NAME ="Report_recco";
        public static final String COLUMN_ENTRY_ID ="id";
        public static final String COLUMN_EA ="ea";
        public static final String COLUMN_EB ="eb";
        public static final String COLUMN_EC ="ec";
        public static final String COLUMN_ED ="ed";
        public static final String COLUMN_EE ="ee";
        public static final String COLUMN_EF ="ef";
        public static final String COLUMN_EG ="eg";
        public static final String COLUMN_EH ="eh";

        public  static Uri buildJobUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri BuildExpId(String id){//Dont delete

            return CONTENT_URI.buildUpon().appendPath(id).build();
        }


        public static String getIDByFromUri(Uri uri){

            return uri.getPathSegments().get(1);
        }
        public static String getCategory(Uri uri){
            String query = uri.getLastPathSegment().toLowerCase();
            return query;
        }


    }



    public static final class AllEntries implements BaseColumns {

        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALL).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ALL;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ALL;

        public static final String TABLE_NAME ="Subjects";

        public static final String COLUMN_EA ="ea";
        public static final String COLUMN_EB ="eb";
        public static final String COLUMN_EC ="ec";
        public static final String COLUMN_ED ="ed";
        public static final String COLUMN_EE ="ee";

        public  static Uri buildJobUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri BuildExpId(String id){//Dont delete

            return CONTENT_URI.buildUpon().appendPath(id).build();
        }


        public static String getSuggest(Uri uri){
            String query = uri.getLastPathSegment().toLowerCase();
            return query;
        }

        public static String getIDByFromUri(Uri uri){

            return uri.getPathSegments().get(1);
        }


    }

    public static final class SUGGEST implements BaseColumns {

        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALL).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Path_SUGGEST;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + Path_SUGGEST;

        public static final String TABLE_NAME ="SG";

        public static final String COLUMN_EA ="ea";
        public static final String COLUMN_EB ="eb";
        public static final String COLUMN_EC ="ec";
        public static final String COLUMN_ED ="ed";
        public static final String COLUMN_EE ="ee";

        public  static Uri buildJobUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri BuildExpId(String id){//Dont delete

            return CONTENT_URI.buildUpon().appendPath(id).build();
        }


        public static String getSuggest(Uri uri){
            String query = uri.getLastPathSegment().toLowerCase();
            return query;
        }

        public static String getIDByFromUri(Uri uri){

            return uri.getPathSegments().get(1);
        }


    }



}
