package Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qghoha.developerx.careerbuddy.R;
import com.qghoha.developerx.careerbuddy.ResultFragment;

import Edit.EditReport;
import data.DatabaseCrud;
import data.JobContracts;

/**
 * Created by DeveloperX on 28-Sep-17.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.cHolder>
{

    private Cursor mCurso ;
    private Context mContext;
    private int LayId;
    FragmentManager fragmentManager;
    EditReport newFragment ; //This Pops up when a card of experience is clicked

    public ReportAdapter(Context c)
    {
        mContext=c;
        FragmentActivity a;
        a=(FragmentActivity)mContext;
        fragmentManager = a.getSupportFragmentManager();
        newFragment = new EditReport();

    }

    public void swapCursor(Cursor newCursor)
    {
        mCurso =newCursor;
        notifyDataSetChanged();
    }




    @Override
    public cHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        int layoutId0 = R.layout.eces_e ;
        v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId0, parent, false);
        v.setFocusable(true);
        return new cHolder(v);
    }

    @Override
    public void onBindViewHolder(cHolder holder, int position)
    {
        mCurso.moveToPosition(position);

        holder.A.setText(mCurso.getString(ResultFragment.COL_A));
        holder.B.setText(mCurso.getString(ResultFragment.COL_B));
        holder.C.setText(mCurso.getString(ResultFragment.COL_C));
//        holder.D.setText(mCurso.getString(ResultFragment.COL_D));
//        holder.E.setText(mCurso.getString(ResultFragment.COL_E));
    }

    @Override
    public int getItemCount()
    {
        if(null==mCurso)return 0;

        return mCurso.getCount();
    }

    public class cHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView A;
        TextView B;
        TextView C;
        ImageButton delete;

        public cHolder(View view)
        {
            super(view);
            A= view.findViewById(R.id.A);
            B= view.findViewById(R.id.B);
            C= view.findViewById(R.id.C);
            delete=view.findViewById(R.id.remove);

            view.setOnClickListener(this);
            view.setTag("edit");
            delete.setOnClickListener(this);
            delete.setFocusable(true);
            delete.setTag("del");
        }
        @Override
        public void onClick(View v)
        {
            int adapter_posotion = getAdapterPosition();
            mCurso.moveToPosition(adapter_posotion);
            int ID_CULUMN_INDEX = mCurso.getColumnIndex(JobContracts.ReportEntry._ID);
            int p = mCurso.getInt(ID_CULUMN_INDEX);

            if (v.getTag() == "del") {
                DatabaseCrud.ToDeleteReportEntry(mContext,p+"");
                Log.v("del","del");
            } else {

//                Intent i =new Intent(mContext,ExperienceActivity.class);
//                i.setData(JobContracts.ReportEntry.BuildExpId(p+""));
//                mContext.startActivity(i);

            }

        }

    }





}
