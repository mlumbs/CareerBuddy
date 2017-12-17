package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.qghoha.developerx.careerbuddy.R;
import com.qghoha.developerx.careerbuddy.RecommendationsFragment;

import Add.AddReport;


public class Recommendations extends RecyclerView.Adapter<Recommendations.cHolder>
{

    private Cursor mCurso ;
    private Context mContext;

    public Recommendations(Context c)
    {
        mContext=c;

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
        int layoutId0 = R.layout.eces_recommendations;
        v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId0, parent, false);
        v.setFocusable(true);
        return new cHolder(v);
    }

    @Override
    public void onBindViewHolder(cHolder holder, int position)
    {
        mCurso.moveToPosition(position);
        holder.ID.setText(mCurso.getString(RecommendationsFragment.COL_ENTRY));
        holder.A.setText(mCurso.getString(RecommendationsFragment.COL_A));
        holder.B.setText(mCurso.getString(RecommendationsFragment.COL_B));
        holder.C.setText(mCurso.getString(RecommendationsFragment.COL_C));
        holder.D.setText(mCurso.getString(RecommendationsFragment.COL_D));
        holder.E.setText(mCurso.getString(RecommendationsFragment.COL_E));
        holder.F.setText(mCurso.getString(RecommendationsFragment.COL_F));
        holder.G.setText(mCurso.getString(RecommendationsFragment.COL_G));
        holder.H.setText(mCurso.getString(RecommendationsFragment.COL_H));
    }

    @Override
    public int getItemCount()
    {
        if(null==mCurso)return 0;
        return mCurso.getCount();
    }

    public class cHolder extends RecyclerView.ViewHolder
    {
        TextView ID;
        TextView A;
        TextView B;
        TextView C;
        TextView D;
        TextView E;
        TextView F;
        TextView G;
        TextView H;
        public cHolder(View view)
        {
            super(view);
            ID= view.findViewById(R.id.ID);
            A= view.findViewById(R.id.A);
            B= view.findViewById(R.id.B);
            C= view.findViewById(R.id.C);
            D= view.findViewById(R.id.D);
            E= view.findViewById(R.id.E);
            F= view.findViewById(R.id.F);
            G= view.findViewById(R.id.G);
            H= view.findViewById(R.id.H);
        }
    }


}
