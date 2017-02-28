package com.cedr.webdev_conference_finished.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cedr.webdev_conference_finished.R;
import com.cedr.webdev_conference_finished.models.CheeseModel;

import de.hdodenhof.circleimageview.CircleImageView;


public class CheeseItemViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private CircleImageView mImgCheese;
    private TextView mTxtCheese;
    private TextView mTxtCheeseInfo;

    public CheeseItemViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
        mImgCheese = (CircleImageView) itemView.findViewById(R.id.imgCheese);
        mTxtCheese = (TextView) itemView.findViewById(R.id.txtCheese);
        mTxtCheeseInfo = (TextView) itemView.findViewById(R.id.txtCheeseInfo);
    }

    public void bindData (CheeseModel cheeseModel){
        Glide.with(mImgCheese.getContext())
             .load(cheeseModel.CheeseDrawable)
             .fitCenter()
             .into(mImgCheese);

        mTxtCheese.setText(cheeseModel.CheeseDescription);
        mTxtCheeseInfo.setText(cheeseModel.CheeseInfo);
    }

    public View getView() {
        return mView;
    }
}
