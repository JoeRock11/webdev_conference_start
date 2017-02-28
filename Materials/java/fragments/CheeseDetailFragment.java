package com.cedr.webdev_conference_finished.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cedr.webdev_conference_finished.R;


public class CheeseDetailFragment extends BottomSheetDialogFragment {

    private float mDim;
    private float mDefaultDim;
    private boolean mIsDefaultStatusBarColorSet;
    private int mCheeseDrawableResID;

    public static CheeseDetailFragment createInstance (int cheeseDrawableResID){
        CheeseDetailFragment cheeseDetailFragment = new CheeseDetailFragment();
        cheeseDetailFragment.mCheeseDrawableResID = cheeseDrawableResID;

        return cheeseDetailFragment;
    }

    private BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            if (slideOffset == 1.0f){
                setDim(0f);
                setStatusBarColor(R.color.colorNeutral);
            }

            else {
                setDefaultStatusBarColor();
                setDim(mDefaultDim);
            }
        }
    };

    private void setStatusBarColor (int colorResId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mIsDefaultStatusBarColorSet){
                getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), colorResId));
                mIsDefaultStatusBarColorSet = false;
            }
        }
    }

    private void setDefaultStatusBarColor (){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!mIsDefaultStatusBarColorSet) {
                TypedValue typedValue = new TypedValue();
                getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
                int color = typedValue.data;
                getActivity().getWindow().setStatusBarColor(color);
                mIsDefaultStatusBarColorSet = true;
            }
        }
    }

   private void setDim(float dim){
       if (mDim == dim) {
           return;
       }

       mDim = dim;
       Window window = getDialog().getWindow();
       if (window != null) {
           WindowManager.LayoutParams windowParams;
           windowParams = window.getAttributes();
           windowParams.dimAmount = mDim;
           windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
           window.setAttributes(windowParams);
       }
  }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        setDefaultStatusBarColor();
    }

    @Override
    public void onStart() {
        super.onStart();

        mDefaultDim = 0.5f;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (getDialog().getWindow() != null) {
                final WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                getDialog().getWindow().setAttributes(params);
            }
        }

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        setStyle(BottomSheetDialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);

        View contentView = View.inflate(getContext(), R.layout.fragment_cheese_detail, null);

        ImageView imageCheese = (ImageView) contentView.findViewById(R.id.imgCheese);
        Glide.with(getContext()).load(mCheeseDrawableResID).into(imageCheese);

        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)((View)contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(callback);
        }
    }
}
