package Edit;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.qghoha.developerx.careerbuddy.R;

import data.DatabaseCrud;
import data.JobContracts;

/**
 * Created by DeveloperX on 28-Sep-17.
 */

public class EditReport extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {

    EditText AA ;
    EditText BB ;
    EditText CC ;
    String id;
    private Uri mUri;


    public static final String EDIT_URI_ID = "URI";

    private static final int E_LOADER = 0;
    private static final String[] E_DETAIL_COLUMNS = {
            JobContracts.ReportEntry._ID ,
            JobContracts.ReportEntry.COLUMN_EA,
            JobContracts.ReportEntry.COLUMN_EB,
            JobContracts.ReportEntry.COLUMN_EC,
    };

    static final int COL_ID = 0;
    static final int COL_EA = 1;
    static final int COL_EB= 2;
    static final int COL_EC=3;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if ( null != mUri ) {
            return new CursorLoader(
                    getActivity(),
                    mUri,
                   E_DETAIL_COLUMNS,
                    null,
                    null,
                    null
            );

        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {

            AA.setText(data.getString(COL_EA));
            BB.setText(data.getString(COL_EB));
            CC.setText(data.getString(COL_EC));
        }

        id=data.getString(COL_ID);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment

        Bundle arguments = getArguments();
        if (arguments != null)
        {
            mUri = arguments.getParcelable(EditReport.EDIT_URI_ID);
        }
     View rootView = inflater.inflate(R.layout.activity_enter, container, false);

     //   AA= (EditText) rootView.findViewById(R.id.AA);
      //  BB= (EditText) rootView.findViewById(R.id.BB);
      //  CC= (EditText) rootView.findViewById(R.id.CC);

//        ImageButton b = (ImageButton) rootView.findViewById(R.id.update);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DatabaseCrud.ToUpdateReportEntry(getActivity(),id,AA.getText().toString(),BB.getText().toString(),CC.getText().toString(),DD.getText().toString(),EE.getText().toString(),FF.getText().toString(),"","","","");
//            }
//        });



        return rootView;
    }



}
