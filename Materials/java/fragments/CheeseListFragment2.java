package com.cedr.webdev_conference_finished.fragments;

import android.view.View;

import com.cedr.webdev_conference_finished.models.CheeseModel;

public class CheeseListFragment2 extends BaseCheeseFragment {

    public static CheeseListFragment2 createInstance (int layoutResId, LayoutManager layoutManager){
        CheeseListFragment2 cheeseFragment = new CheeseListFragment2();
        cheeseFragment.mLayoutResId = layoutResId;
        cheeseFragment.mLayoutManagerType = layoutManager;

        return cheeseFragment;
    }

    @Override
    protected void onViewCreated(View view) {
    }

    @Override
    protected void onItemClicked(View view, CheeseModel cheese) {
        CheeseDetailFragment fragment = CheeseDetailFragment.createInstance(cheese.CheeseDrawable);
        fragment.show(getChildFragmentManager(), "Details");
    }
}
