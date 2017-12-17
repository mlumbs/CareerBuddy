package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.qghoha.developerx.careerbuddy.R;

import Add.AddReport;
import Edit.EditReport;
import data.DatabaseCrud;
import data.JobContracts;


public class AddAdapters extends RecyclerView.Adapter<AddAdapters.cHolder>
{

    private Cursor mCurso ;
    private Context mContext;
    private int LayId;
    FragmentManager fragmentManager;
    EditReport newFragment ; //This Pops up when a card of experience is clicked
    ArrayAdapter<CharSequence> adapter ;
    String code;
    int currentSP;
    boolean currentCK;
// Specify the layout to use when the list of choices appears


    public AddAdapters(Context c)
    {
        mContext=c;
        FragmentActivity a;
        a=(FragmentActivity)mContext;
        fragmentManager = a.getSupportFragmentManager();
        newFragment = new EditReport();
        adapter = ArrayAdapter.createFromResource(c,
                R.array.levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     code="RRR";
        currentSP =0;
        currentCK=false;
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
        int layoutId0 = R.layout.eces_a ;
        v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId0, parent, false);
        v.setFocusable(true);
        return new cHolder(v);
    }

    @Override
    public void onBindViewHolder(cHolder holder, int position)
    {

        mCurso.moveToPosition(position);
        //currentSP =Integer.parseInt(mCurso.getString(AddReport.COL_C));
        holder.A.setText(mCurso.getString(AddReport.COL_B));
        currentCK=returnV(mCurso.getString(AddReport.COL_D));
        holder.mReceive.setChecked(currentCK);

        holder.SP.setAdapter(adapter);
        holder.SP.setSelection(Integer.parseInt(mCurso.getString(AddReport.COL_C)));
       // holder.SP.setSelection(adapter.getPosition(currentSP+""));
        code=mCurso.getString(AddReport.COL_A);
        //holder.SP.setAdapter(adapter);
    }

    @Override
    public int getItemCount()
    {
        if(null==mCurso)return 0;

        return mCurso.getCount();
    }

    public class cHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,AdapterView.OnItemSelectedListener
    {
        TextView A;
        //TextView B;
        Spinner SP;
        CompoundButton mReceive;
        public cHolder(View view)
        {
            super(view);
            A= view.findViewById(R.id.A);
            mReceive=view.findViewById(R.id.include);
            //B= view.findViewById(R.id.B);
            SP=view.findViewById(R.id.spinner);
           // SP.setAdapter(adapter);
           // SP.setOnItemSelectedListener(this);
            mReceive.setOnClickListener(this);
            mReceive.setTag("1");


            //SP.setTag("2");
        }
        @Override
        public void onClick(View v) {
            int adapter_posotion = getAdapterPosition();
            mCurso.moveToPosition(adapter_posotion);
            int ID_CULUMN_INDEX = mCurso.getColumnIndex(JobContracts.AllEntries.COLUMN_EA);
            int p = mCurso.getInt(ID_CULUMN_INDEX);

            Log.v("sp", SP.getLastVisiblePosition()+"");
            if (v.getTag() == "1") {
                if (mReceive.isChecked()) {
                    //DatabaseCrud.ToUpdateAllEntry(mContext,mCurso.getString(AddReport.COL_ID),"",returnV(currentCK));
                    DatabaseCrud.ToUpdate_che(mContext, mCurso.getString(AddReport.COL_A), "1");
                    //DatabaseCrud.ToUpdateAllEntry(mContext,mCurso.getString(AddReport.COL_ID), currentSP +"","1");
                    DatabaseCrud.ToUpdate_sp(mContext,mCurso.getString(AddReport.COL_A),SP.getLastVisiblePosition()+"");
                   // DatabaseCrud.ToAaddReportEntry(mContext, mCurso.getString(AddReport.COL_A), mCurso.getString(AddReport.COL_B) , mCurso.getString(AddReport.COL_C), "C", "D");
                    DatabaseCrud.ToAaddReportEntry(mContext, mCurso.getString(AddReport.COL_A), mCurso.getString(AddReport.COL_B) , SP.getLastVisiblePosition()+"", "C", "D");

                } else {
                    DatabaseCrud.ToUpdate_che(mContext, mCurso.getString(AddReport.COL_A), "0");
                    int vy = DatabaseCrud.ToUpdate_sp(mContext, mCurso.getString(AddReport.COL_A), "0");
                    DatabaseCrud.ToDeleteReportEntry(mContext,mCurso.getString(AddReport.COL_A));
                    //DatabaseCrud.ToUpdateAllEntry(mContext,mCurso.getString(AddReport.COL_ID),"","0");
                    Log.v("clicked_check", p + "false" + " " + vy);
                    // notifyDataSetChanged();
                }

            }




        }

//         SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            // SP.setSelection(i);
//            String b= (String) adapterView.getItemAtPosition(i);
//            DatabaseCrud.ToUpdateAllEntry(mContext,mCurso.getString(AddReport.COL_ID),b+"",returnV(currentCK));
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Log.v("repeat","repeat");
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }


    }
//Integer.parseInt(value)
public boolean returnV(String t){
    if(Integer.parseInt(t)==1)
        return true;
    else
        return false;

}
    public String returnV(boolean t){
        if(t)
            return "1";
        else
            return "0";

    }


}
