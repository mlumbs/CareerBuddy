package Add;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qghoha.developerx.careerbuddy.R;
import com.qghoha.developerx.careerbuddy.ResultFragment;

import Adapters.AddAdapters;
import Adapters.ReportAdapter;
import data.DatabaseCrud;
import data.JobContracts;

/**
 * Created by DeveloperX on 28-Sep-17.
 */

public class AddReport extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    public static final String LOG= ResultFragment.class.getSimpleName();
    public static final int REPORT_LOADER = 0;

    public static final String[]  EXPERIENCE_COLUMNS = {
            JobContracts.AllEntries._ID ,
            JobContracts.AllEntries.COLUMN_EA,
            JobContracts.AllEntries.COLUMN_EB,
            JobContracts.AllEntries.COLUMN_EC,
            JobContracts.AllEntries.COLUMN_ED,
            JobContracts.AllEntries.COLUMN_EE,
    };
    public static final int COL_ID= 0;
    public static final int COL_A = 1;
    public static final int COL_B = 2;
    public static final int COL_C= 3;
    public static final int COL_D= 4;

    private AddAdapters mAdapter;
    public AddReport() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerview_entity);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        mAdapter = new AddAdapters(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return rootView;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder = JobContracts.AllEntries.COLUMN_EB + " ASC";
        return new CursorLoader(getActivity(),
                JobContracts.AllEntries.CONTENT_URI,
                EXPERIENCE_COLUMNS,
                null,
                null,
                sortOrder);
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
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(REPORT_LOADER, null, this);

    }
    }
