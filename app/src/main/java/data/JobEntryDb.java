package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by DevelopX on 2015-09-04.
 */
public class JobEntryDb extends SQLiteOpenHelper {

    static final int DATABASE_VERSION =1;
    static final String DATABASE_NAME="career.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String TIMESTAMP = " INTEGER";

    public JobEntryDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE =  "CREATE TABLE " + JobContracts.ReportEntry.TABLE_NAME + " (" +
                JobContracts.ReportEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +COMMA_SEP+
                JobContracts.ReportEntry.COLUMN_EA + TEXT_TYPE + COMMA_SEP +
                JobContracts.ReportEntry.COLUMN_EB + TEXT_TYPE + COMMA_SEP +
                JobContracts.ReportEntry.COLUMN_EC +TEXT_TYPE+COMMA_SEP+
                JobContracts.ReportEntry.COLUMN_ED +TEXT_TYPE+COMMA_SEP+
                JobContracts.ReportEntry.COLUMN_EE +TEXT_TYPE+
                " )";


        String CREATE_TABLE_ALL =  "CREATE TABLE " + JobContracts.AllEntries.TABLE_NAME + " (" +
                JobContracts.AllEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +COMMA_SEP+
                JobContracts.AllEntries.COLUMN_EA + TEXT_TYPE + COMMA_SEP +
                JobContracts.AllEntries.COLUMN_EB + TEXT_TYPE + COMMA_SEP +
                JobContracts.AllEntries.COLUMN_EC +TEXT_TYPE+COMMA_SEP+
                JobContracts.AllEntries.COLUMN_ED +TEXT_TYPE+COMMA_SEP+
                JobContracts.AllEntries.COLUMN_EE +TEXT_TYPE+
                " )";

        String CREATE_TABLE_SUGG =  "CREATE TABLE " + JobContracts.SUGGEST.TABLE_NAME + " (" +
                JobContracts.SUGGEST._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +COMMA_SEP+
                JobContracts.SUGGEST.COLUMN_EA + TEXT_TYPE + COMMA_SEP +
                JobContracts.SUGGEST.COLUMN_EB + TEXT_TYPE + COMMA_SEP +
                JobContracts.SUGGEST.COLUMN_EC +TEXT_TYPE+COMMA_SEP+
                JobContracts.SUGGEST.COLUMN_ED +TEXT_TYPE+COMMA_SEP+
                JobContracts.SUGGEST.COLUMN_EE +TEXT_TYPE+
                " )";

        String CREATE_TABLE_RECCO =  "CREATE TABLE " + JobContracts.Report_reco.TABLE_NAME + " (" +
                JobContracts.Report_reco._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +COMMA_SEP+
                JobContracts.Report_reco.COLUMN_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                JobContracts.Report_reco.COLUMN_EA + TEXT_TYPE + COMMA_SEP +
                JobContracts.Report_reco.COLUMN_EB + TEXT_TYPE + COMMA_SEP +
                JobContracts.Report_reco.COLUMN_EC +TEXT_TYPE+COMMA_SEP+
                JobContracts.Report_reco.COLUMN_ED +TEXT_TYPE+COMMA_SEP+
                JobContracts.Report_reco.COLUMN_EF + TEXT_TYPE + COMMA_SEP +
                JobContracts.Report_reco.COLUMN_EG +TEXT_TYPE+COMMA_SEP+
                JobContracts.Report_reco.COLUMN_EH +TEXT_TYPE+COMMA_SEP+
                JobContracts.Report_reco.COLUMN_EE +TEXT_TYPE+
                " )";

        db.execSQL(CREATE_TABLE_RECCO);
        db.execSQL(CREATE_TABLE_SUGG);
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_ALL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ JobContracts.ReportEntry.TABLE_NAME);
        onCreate(db);
    }

}
