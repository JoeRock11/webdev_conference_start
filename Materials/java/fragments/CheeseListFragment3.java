package com.cedr.webdev_conference_finished.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.cedr.webdev_conference_finished.activities.CheeseDetailActivity;
import com.cedr.webdev_conference_finished.models.CheeseModel;


public class CheeseListFragment3 extends BaseCheeseFragment {

    public static CheeseListFragment3 createInstance (int layoutResId, LayoutManager layoutManager){
        CheeseListFragment3 cheeseFragment = new CheeseListFragment3();
        cheeseFragment.mLayoutResId = layoutResId;
        cheeseFragment.mLayoutManagerType = layoutManager;

        return cheeseFragment;
    }

    @Override
    protected void onViewCreated(View view) {
    }

    @Override
    protected void onItemClicked(View view, CheeseModel cheese) {
        Intent intent = new Intent(getContext(), CheeseDetailActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        }
    }

}
