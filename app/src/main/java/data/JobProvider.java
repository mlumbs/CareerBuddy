package data;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static android.app.SearchManager.SUGGEST_URI_PATH_QUERY;

//import debugging.Logs;

/**
 * Created by DevelopX on 2015-09-04.
 * when Using this class dont forget to update the Manifest
 */
public class JobProvider extends ContentProvider {

    private static final int experience = 101;
    private static final int experience_id =102;
    private static final int education_recomment =109;
    private static final int education = 103;
    private static final int education_id =104;

    private static final int education_suggest =106;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private JobEntryDb mDbHelper;


    private static UriMatcher buildUriMatcher() {
        //content://com.qghoha.developerx.careerbuddy/
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = JobContracts.CONTENT_AUTHORITY;


        matcher.addURI(authority, JobContracts.PATH_REPORT , experience);
        matcher.addURI(authority, JobContracts.PATH_REPORT+ "/*", experience_id);

        matcher.addURI(authority, JobContracts.PATH_ALL , education);
        matcher.addURI(authority, JobContracts.Path_SUGGEST+"/*", education_suggest);
        matcher.addURI(authority, JobContracts.PATH_ALL+ "/*", education_id);

        matcher.addURI(authority, JobContracts.PATH_RECCO+ "/*",  education_recomment);
        matcher.addURI(authority, JobContracts.PATH_RECCO,  education_recomment);

        return matcher;
    }


    @Override
    public boolean onCreate() {
        mDbHelper = new JobEntryDb(getContext());
        return true;
    }


    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {

            case experience:
                return JobContracts.ReportEntry.CONTENT_TYPE;
            case experience_id:
                return JobContracts.ReportEntry.CONTENT_ITEM_TYPE;
            case education:
                return JobContracts.AllEntries.CONTENT_TYPE;
            case education_id:
                return JobContracts.AllEntries.CONTENT_ITEM_TYPE;
            case education_suggest:
                return JobContracts.SUGGEST.CONTENT_TYPE;
            case education_recomment:
                return JobContracts.Report_reco.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {

          case experience:
            {
                retCursor = mDbHelper.getReadableDatabase().query(
                        JobContracts.ReportEntry.TABLE_NAME,
                        null,
                        null,//mSelectionClause
                        null,//mSelectionArgs
                        null,
                        null,
                        sortOrder);
                break;
            }
            case education:
            {
                retCursor = mDbHelper.getReadableDatabase().query(
                        JobContracts.AllEntries.TABLE_NAME,
                        null,
                        null,//mSelectionClause
                        null,//mSelectionArgs
                        null,
                        null,
                        sortOrder);
                break;
            }
            case experience_id:
            {
                retCursor = getReportId(uri,JobContracts.ReportEntry.TABLE_NAME + "." + JobContracts.ReportEntry._ID + " = ?",JobContracts.ReportEntry.TABLE_NAME);
                break;
            }
            case education_recomment:
            {
                retCursor = getReportChoice(uri,JobContracts.Report_reco.TABLE_NAME + "." + JobContracts.Report_reco.COLUMN_EA + " = ?",JobContracts.Report_reco.TABLE_NAME);
                break;
            }

            case education_suggest:
            {
                Log.v("PR",uri+"-->");
                // retCursor = getSubjectName(uri,JobContracts.AllEntries.TABLE_NAME + "." + JobContracts.AllEntries.COLUMN_EA+ " = ?",JobContracts.AllEntries.TABLE_NAME);
                retCursor = getSubjectName(uri,JobContracts.AllEntries.COLUMN_EB,JobContracts.AllEntries.TABLE_NAME);

                break;
            }
            case education_id:
            {
  // retCursor = getSubjectName(uri,JobContracts.AllEntries.TABLE_NAME + "." + JobContracts.AllEntries.COLUMN_EA+ " = ?",JobContracts.AllEntries.TABLE_NAME);
                retCursor = getSubjectId(uri,JobContracts.AllEntries.TABLE_NAME + "." + JobContracts.AllEntries._ID+" = ?",JobContracts.AllEntries.TABLE_NAME);

                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown " + uri);
        }

       // uri= JobContracts.ExperienceEntry.CONTENT_URI; //uncomment this if errors persits
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    private Cursor getSubjectId(Uri uri, String mSelectionClause , String table) {

        String Id = JobContracts.AllEntries.getIDByFromUri(uri); //Calling for each type ,I believe it a formality not a necessity required
        String[] mSelectionArgs = {""};//But eventually the execution will not be different since the methods are static
        mSelectionArgs[0] = Id;
        //String mSelectionClause = JobContracts.ExperienceEntry.TABLE_NAME + "." + JobContracts.ExperienceEntry._ID + " = ?";

        //Logs.appendLog("contact:uri","Quary L2 "+uri);
        return mDbHelper.getReadableDatabase().query(
                table,
                null,
                mSelectionClause,
                mSelectionArgs,
                null,
                null,
                null);

    }

    private Cursor getSubjectName(Uri uri, String mSelectionClause , String table) {

        String Id = JobContracts.AllEntries.getSuggest(uri); //Calling for each type ,I believe it a formality not a necessity required
        String[] mSelectionArgs = {""};//But eventually the execution will not be different since the methods are static
        mSelectionArgs[0] = Id;
        //String mSelectionClause = JobContracts.AllEntries.TABLE_NAME + "." + JobContracts.AllEntries._ID + " = ?";
        Log.v("PR2",Id);
        //Logs.appendLog("contact:uri","Quary L2 "+uri);
        return mDbHelper.getReadableDatabase().query(
                table,
                null,
                null,
                mSelectionArgs,
                null,
                null,
                null);

    }



    private Cursor getReportId(Uri uri, String mSelectionClause , String table) {

        String Id = JobContracts.ReportEntry.getIDByFromUri(uri); //Calling for each type ,I believe it a formality not a necessity required
        String[] mSelectionArgs = {""};//But eventually the execution will not be different since the methods are static
        mSelectionArgs[0] = Id;
        return mDbHelper.getReadableDatabase().query(
                table,
                null,
                mSelectionClause,
                mSelectionArgs,
                null,
                null,
                null);

    }



    private Cursor getReportChoice(Uri uri, String mSelectionClause , String table) {
        String Id = JobContracts.ReportEntry.getIDByFromUri(uri); //Calling for each type i.e ranges from 1 to 3; 1 means calling all choice 1 attributes
        String[] mSelectionArgs = {""};//But eventually the execution will not be different since the methods are static
        mSelectionArgs[0] = Id;
        return mDbHelper.getReadableDatabase().query(
                table,
                null,
                mSelectionClause,
                mSelectionArgs,
                null,
                null,
                null);
    }




    @Override
    public Uri insert(Uri uri, ContentValues values) {


        final SQLiteDatabase db =mDbHelper.getWritableDatabase();
        final int match =sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {

            case experience: {
                long _id = db.insert(JobContracts.ReportEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = JobContracts.ReportEntry.buildJobUri(_id);
                    Log.v("CRUD",_id+"");
                } else
                    throw new android.database.SQLException("Failed to insert A row");
                break;
            }
            case education_recomment: {
                long _id = db.insert(JobContracts.Report_reco.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = JobContracts.Report_reco.buildJobUri(_id);
                    Log.v("CRUD",_id+" education_recomment");
                } else
                    throw new android.database.SQLException("Failed to insert A row");
                break;
            }
            case education: {
                long _id = db.insert(JobContracts.AllEntries.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = JobContracts.AllEntries.buildJobUri(_id);
                } else
                    throw new android.database.SQLException("Failed to insert A row");
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri "+uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);//notifies conten observer that the data ha changed see vid 24
        //db.close();
        return returnUri;




    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db =mDbHelper.getWritableDatabase();
        final int match =sUriMatcher.match(uri);
        int rowsDeleted=0;

        switch (match) {

            case experience_id:
                rowsDeleted=db.delete(JobContracts.ReportEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case education_id:
                rowsDeleted=db.delete(JobContracts.AllEntries.TABLE_NAME,selection,selectionArgs);
                break;
            case education_recomment:
                rowsDeleted=db.delete(JobContracts.Report_reco.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri"+uri);
        }
        if (null==selection ||0!=rowsDeleted){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {


            final SQLiteDatabase db=mDbHelper.getWritableDatabase();
            final int match =sUriMatcher.match(uri);
            int rowsUpdated;

            switch (match) {

                case experience_id:
                   rowsUpdated = db.update(JobContracts.ReportEntry.TABLE_NAME, values, selection,
                           selectionArgs);
                    break;
                case education_id:
                    rowsUpdated = db.update(JobContracts.AllEntries.TABLE_NAME, values, selection,
                            selectionArgs);
                    break;

                default:
                    throw new UnsupportedOperationException("Unknown uri"+uri);
            }
            if (null==selection ||0!=rowsUpdated){
                getContext().getContentResolver().notifyChange(uri, null);
                Log.v("Update","updates");
            }


            return rowsUpdated;

    }


}
