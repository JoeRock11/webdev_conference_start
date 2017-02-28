package com.cedr.webdev_conference_finished.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cedr.webdev_conference_finished.fragments.BaseCheeseFragment;
import com.cedr.webdev_conference_finished.models.CheeseModel;
import com.cedr.webdev_conference_finished.viewholders.CheeseItemViewHolder;

import java.util.List;

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<CheeseItemViewHolder> {

    private List<CheeseModel> mCheeseDataSet;
    private BaseCheeseFragment.OnItemClickedListener mListener;
    private int mLayoutResId;
    private RecyclerView mRecyclerView;

    public SimpleRecyclerViewAdapter (int layoutResId, List<CheeseModel> cheeseDataSet,
                                      BaseCheeseFragment.OnItemClickedListener listener){
        mLayoutResId = layoutResId;
        mCheeseDataSet = cheeseDataSet;
        mListener = listener;
    }

    @Override
    public CheeseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(mLayoutResId, parent, false);

        return new CheeseItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CheeseItemViewHolder holder, int position) {
        holder.bindData(mCheeseDataSet.get(position));

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = mRecyclerView.getChildAdapterPosition(view);
                if (mListener != null){
                    mListener.itemClicked(view, mCheeseDataSet.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCheeseDataSet.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }
}
