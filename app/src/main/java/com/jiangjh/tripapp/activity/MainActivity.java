package com.jiangjh.tripapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jiangjh.tripapp.R;
import com.jiangjh.tripapp.fragment.DestinationFragment;
import com.jiangjh.tripapp.fragment.HomeFragment;
import com.jiangjh.tripapp.fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    private static final String TAG = "MainActivity";
    BottomNavigationBar bottomNavigationBar;
    private int currentPosition = 0;
    private Fragment fg1, fg2, fg3;
    private FragmentManager fragmentManager;
    private boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            fg1 = fragmentManager.findFragmentByTag(HomeFragment.class.getName());
            fg2 = fragmentManager.findFragmentByTag(DestinationFragment.class.getName());
            fg3 = fragmentManager.findFragmentByTag(UserFragment.class.getName());
        }
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);


        initBottomBar();
    }

    private void initBottomBar() {
        if (bottomNavigationBar == null) {
            return;
        }
        int currentSelectedPosition = bottomNavigationBar.getCurrentSelectedPosition();
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setBarBackgroundColor("#F5F5F6");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor("#567FD3");
        bottomNavigationBar.setInActiveColor("#525252");
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_home_press, "首页")
                .setInactiveIconResource(R.mipmap.icon_home_unpress));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_destination_press, "目的地")
                .setInactiveIconResource(R.mipmap.icon_destination_unpress));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_mine_press, "我的")
                .setInactiveIconResource(R.mipmap.icon_mine_unpress));

//        }
        bottomNavigationBar.initialise();
        if (currentSelectedPosition == currentPosition) {
            return;
        }
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.selectTab(currentPosition);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onTabSelected(int position) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideAllFragment(ft);
        switch (position) {
            case 0:
                if (fg1 == null) {
                    fg1 = HomeFragment.newInstance();
                    ft.add(R.id.layFrame, fg1, HomeFragment.class.getName());
                } else {
                    ft.show(fg1);
                }
                break;
            case 1:
                if (fg2 == null) {
                    fg2 = DestinationFragment.newInstance();
                    ft.add(R.id.layFrame, fg2, DestinationFragment.class.getName());
                } else {
                    ft.show(fg2);
                }
                break;
            case 2:
                if (fg3 == null) {
                    fg3 = UserFragment.newInstance();
                    ft.add(R.id.layFrame, fg3, UserFragment.class.getName());
                } else {
                    ft.show(fg3);
                }
                break;
            default:
                break;
        }
        ft.commitAllowingStateLoss();
        currentPosition = position;


    }


    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }
    }


    @Override
    public void onBackPressed() {
        if (!isBack) {
//            ToastUtils.showToastLong(this, "再按一次，退出应用");
            isBack = true;
        } else {
            moveTaskToBack(true);
        }
    }


}
