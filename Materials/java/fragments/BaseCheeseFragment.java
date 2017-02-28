package com.cedr.webdev_conference_finished.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cedr.webdev_conference_finished.R;
import com.cedr.webdev_conference_finished.adapters.SimpleRecyclerViewAdapter;
import com.cedr.webdev_conference_finished.models.CheeseModel;
import com.cedr.webdev_conference_finished.utilities.Cheeses;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseCheeseFragment extends Fragment {


    public interface OnItemClickedListener{
        void itemClicked (View view, CheeseModel cheese);
    }

    protected LayoutManager mLayoutManagerType;
    protected List<CheeseModel> mCheeses;
    protected int mLayoutResId;

    protected abstract void onViewCreated(View view);
    protected abstract void onItemClicked (View view, CheeseModel cheese);

    public enum LayoutManager{
        LinearLayout,
        StaggeredLayout
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCheeses = new ArrayList<>();
        mCheeses.addAll(Cheeses.getRandomCheeses(getContext(), 30));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cheese_list, container,  false);

        onViewCreated(view);

        setupRecyclerView(view);

        return view;
    }


    private void setupRecyclerView (View view){

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        if (mLayoutResId == 0){
            mLayoutResId = R.layout.item_cheese;
        }

        RecyclerView.LayoutManager layoutManager;

        if (mLayoutManagerType == null){
            layoutManager = new LinearLayoutManager(getContext());
        }

        else{
            switch (mLayoutManagerType){
                case LinearLayout:
                    layoutManager = new LinearLayoutManager(getContext());
                    break;

                case StaggeredLayout:
                    layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    break;

                default:
                    layoutManager = new LinearLayoutManager(getContext());
            }
        }

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(mLayoutResId, mCheeses, new OnItemClickedListener() {
            @Override
            public void itemClicked(View view, CheeseModel cheese) {
                onItemClicked(view, cheese);
            }
        }));
    }
}
