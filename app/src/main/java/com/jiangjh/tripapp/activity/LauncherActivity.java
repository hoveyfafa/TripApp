package com.jiangjh.tripapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.fragment.LauncherFragment1;
import com.jiangjh.tripapp.fragment.LauncherFragment2;
import com.jiangjh.tripapp.fragment.LauncherFragment3;
import com.jiangjh.tripapp.fragment.LauncherFragment4;

import me.relex.circleindicator.CircleIndicator;

/**
 * @author Jinghao.Jiang
 * @date 2018/2/20
 */

public class LauncherActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.launcher_layout);
        mViewPager = (ViewPager) findViewById(R.id.launcher_viewpager);
        mCircleIndicator = (CircleIndicator) findViewById(R.id.launcher_indicator);

        final MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mCircleIndicator.setViewPager(mViewPager);
        pagerAdapter.registerDataSetObserver(mCircleIndicator.getDataSetObserver());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int currentPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                if (currentPosition == 3) {
                    mCircleIndicator.setVisibility(View.INVISIBLE);
                } else {
                    mCircleIndicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        LauncherFragment1 mFragment1;
        LauncherFragment2 mFragment1_2;
        LauncherFragment3 mFragment1_3;
        LauncherFragment4 mFragment1_4;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mFragment1 == null) {
                        mFragment1 = new LauncherFragment1();
                    }
                    return mFragment1;
                case 1:
                    if (mFragment1_2 == null) {
                        mFragment1_2 = new LauncherFragment2();
                    }
                    return mFragment1_2;
                case 2:
                    if (mFragment1_3 == null) {
                        mFragment1_3 = new LauncherFragment3();
                    }
                    return mFragment1_3;
                case 3:
                    if (mFragment1_4 == null) {
                        mFragment1_4 = new LauncherFragment4();
                    }
                    return mFragment1_4;
                default:
                    break;
            }
            return new LauncherFragment1();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
