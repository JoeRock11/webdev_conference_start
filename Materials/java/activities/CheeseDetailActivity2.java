package com.cedr.webdev_conference_finished.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cedr.webdev_conference_finished.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CheeseDetailActivity2 extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mImgCheeseLarge;
    private CircleImageView mImgCheese;
    private TextView mTxtCheeseName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_detail_2);

        bindActivity();

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        mToolbar.inflateMenu(R.menu.sample_actions);

        Glide.with(this).load(getIntent().getIntExtra("DrawableResID", 0)).into(mImgCheese);
        Glide.with(this).load(getIntent().getIntExtra("DrawableResID", 0)).into(mImgCheeseLarge);

        mTxtCheeseName.setText(getIntent().getStringExtra("CheeseName"));
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mImgCheeseLarge = (ImageView) findViewById(R.id.main_imageview_placeholder);
        mImgCheese      = (CircleImageView) findViewById(R.id.imgCheese);
        mTxtCheeseName = (TextView) findViewById(R.id.txtCheeseName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

}
