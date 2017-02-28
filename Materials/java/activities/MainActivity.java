package com.cedr.webdev_conference_finished.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

import com.cedr.webdev_conference_finished.R;
import com.cedr.webdev_conference_finished.adapters.ViewPagerAdapter;
import com.cedr.webdev_conference_finished.fragments.BaseCheeseFragment;
import com.cedr.webdev_conference_finished.fragments.CheeseListFragment1;
import com.cedr.webdev_conference_finished.fragments.CheeseListFragment2;
import com.cedr.webdev_conference_finished.fragments.CheeseListFragment3;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new Fade());
        }

        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null){
            setupDrawerContent(navigationView);
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .show();
            }
        });

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null){
            setUpViewPager(viewPager);

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(final int position) {
                    ScaleAnimation animation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(150);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            switch (position){
                                case 0:
                                    fab.setImageDrawable(ContextCompat.getDrawable(viewPager.getContext(), R.drawable.ic_done));
                                    break;

                                case 1:
                                    fab.setImageDrawable(ContextCompat.getDrawable(viewPager.getContext(), R.drawable.ic_comment));
                                    break;

                                case 2:
                                    fab.setImageDrawable(ContextCompat.getDrawable(viewPager.getContext(), R.drawable.ic_headset));
                                    break;
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    fab.startAnimation(animation);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CheeseListFragment1.createInstance(
                    R.layout.item_cheese,
                    BaseCheeseFragment.LayoutManager.LinearLayout),
                "Cheese 1");

        adapter.addFragment(CheeseListFragment2.createInstance(
                    R.layout.card_cheese,
                BaseCheeseFragment.LayoutManager.LinearLayout),
                "Cheese 2");

        adapter.addFragment(CheeseListFragment3.createInstance(
                    R.layout.card_cheese,
                BaseCheeseFragment.LayoutManager.StaggeredLayout),
                "Cheese 3");


        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    item.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
    }
}
