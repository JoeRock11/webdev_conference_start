package com.cedr.webdev_conference_finished.fragments;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.cedr.webdev_conference_finished.R;
import com.cedr.webdev_conference_finished.activities.CheeseDetailActivity2;
import com.cedr.webdev_conference_finished.models.CheeseModel;


public class CheeseListFragment1 extends BaseCheeseFragment {

    public static CheeseListFragment1 createInstance (int layoutResId, LayoutManager layoutManager){
        CheeseListFragment1 cheeseFragment = new CheeseListFragment1();
        cheeseFragment.mLayoutResId = layoutResId;
        cheeseFragment.mLayoutManagerType = layoutManager;

        return cheeseFragment;
    }

    @Override
    protected void onViewCreated(View view) {
        //Do specific stuff with view
    }

    @Override
    protected void onItemClicked(View view, CheeseModel cheese) {
        Intent intent = new Intent(getContext(), CheeseDetailActivity2.class);
        intent.putExtra("DrawableResID", cheese.CheeseDrawable);
        intent.putExtra("CheeseName", cheese.CheeseDescription);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            view.findViewById(R.id.imgCheese),
                            getString(R.string.activity_image_trans));

            startActivity(intent, optionsCompat.toBundle());
        }

        else{
            startActivity(intent);
        }
    }

}
