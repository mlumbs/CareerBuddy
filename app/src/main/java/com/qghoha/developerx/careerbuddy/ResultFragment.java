package com.qghoha.developerx.careerbuddy;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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


import Adapters.ReportAdapter;
import data.JobContracts;


public class ResultFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String LOG= ResultFragment.class.getSimpleName();
    public static final int REPORT_LOADER = 0;

    public static final String[]  EXPERIENCE_COLUMNS = {
            JobContracts.ReportEntry._ID ,
            JobContracts.ReportEntry.COLUMN_EA,
            JobContracts.ReportEntry.COLUMN_EB,
            JobContracts.ReportEntry.COLUMN_EC,
            JobContracts.ReportEntry.COLUMN_ED,
            JobContracts.ReportEntry.COLUMN_EE,
    };
    public static final int COL_ID= 0;
    public static final int COL_A = 1;
    public static final int COL_B = 2;
    public static final int COL_C= 3;
    public static final int COL_D=4;
    public static final int COL_E= 5;

    private ReportAdapter mAdapter;
    public ResultFragment() {
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
        mAdapter = new ReportAdapter(getActivity());
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
        String sortOrder = JobContracts.ReportEntry._ID + " DESC";
        return new CursorLoader(getActivity(),
                JobContracts.ReportEntry.CONTENT_URI,
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
