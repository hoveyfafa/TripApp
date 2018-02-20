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

//        if (navigationChange) {
//            DTTheme theme = AppConfigModel.INSTANCE.getAppTheme();

//            Navigation navigation = theme.getNavigation();
//            if (navigation == null) {
//                return;
//            }

//            NavigationColor navigationColor = navigation.getColor();
//            bottomNavigationBar.setActiveColor(navigationColor.getActive());
//            bottomNavigationBar.setInActiveColor(navigationColor.getInactive());
//
//            Map<String, NavigationIconDescription> NavigationIconMap = navigation.getIcons();
////            String filePath = FileUtils.getDiskCacheDir() + "navigation" + File.separator;
//            for (Map.Entry<String, NavigationIconDescription> entry : NavigationIconMap.entrySet()) {
//                switch (entry.getKey()) {
//                    case "home":
////                        addNavigationItem(filePath, entry, R.string.home_page_title,
////                                R.mipmap.icon_home_selected, R.mipmap.icon_home_unselected);
//                        break;
//                    case "activity":
//                        NavigationIconDescription iconDescription = entry.getValue();
//                        Map<String, String> iconMap = iconDescription.getIcon();
//
//                        Drawable activityDrawable = null;
//                        Drawable inactiveDrawable = null;
//                        Drawable unreadDrawable = null;
//                        String title = null;
//
//                        for (Map.Entry<String, String> iconEntry : iconMap.entrySet()) {
//                            String iconFilePath = filePath + entry.getKey() + "_" + iconEntry.getKey() + ".png";
//                            File iconFile = new File(iconFilePath);
//                            if (!iconFile.exists()) {
//                                continue;
//                            }
//                            switch (iconEntry.getKey()) {
//                                case "active":
//                                    activityDrawable = Drawable.createFromPath(iconFilePath);
//                                    break;
//                                case "inactive":
//                                    inactiveDrawable = Drawable.createFromPath(iconFilePath);
//                                    break;
//                                case "unread":
//                                    unreadDrawable = Drawable.createFromPath(iconFilePath);
//                                    break;
//                            }
//                        }
//
//                        if (hasChangePromotion) {
//                            if (activityDrawable != null && inactiveDrawable != null) {
//                                title = iconDescription.getName();
//                            } else {
//                                title = getString(R.string.promotion_page_title);
//                                activityDrawable = getResources().getDrawable(R.mipmap.icon_promotion_selected);
//                                inactiveDrawable = getResources().getDrawable(R.mipmap.icon_promotion_unselected);
//                            }
//
//                        } else {
//                            if (activityDrawable != null && unreadDrawable != null) {
//                                title = iconDescription.getName();
//                            } else {
//                                title = getString(R.string.promotion_page_title);
//                                activityDrawable = getResources().getDrawable(R.mipmap.icon_promotion_selected);
//                                inactiveDrawable = getResources().getDrawable(R.mipmap.icon_unselected_red);
//                            }
//                        }
//                        bottomNavigationBar.addItem(new BottomNavigationItem(activityDrawable, title)
//                                .setInactiveIcon(inactiveDrawable));
//
//                        break;
//                    case "vip":
//                        addNavigationItem(filePath, entry, R.string.vip_page_title,
//                                R.mipmap.icon_vip_selected, R.mipmap.icon_vip_unselected);
//                        break;
//                    case "me":
//                        addNavigationItem(filePath, entry, R.string.user_page_title,
//                                R.mipmap.icon_mine_selected, R.mipmap.icon_mine_unselected);
//                        break;
//                }
//            }

//        } else {
            bottomNavigationBar.setActiveColor("#567FD3");
            bottomNavigationBar.setInActiveColor("#525252");
            bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_home_selected, "首页")
                    .setInactiveIconResource(R.mipmap.icon_home_unselected));
            bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_promotion_selected, "目的地")
                    .setInactiveIconResource(R.mipmap.icon_promotion_unselected));
            bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_mine_selected, "我的")
                    .setInactiveIconResource(R.mipmap.icon_mine_unselected));

//        }
        bottomNavigationBar.initialise();
        if (currentSelectedPosition == currentPosition) {
            return;
        }
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.selectTab(currentPosition);
    }

//    private void addNavigationItem(String filePath, Map.Entry<String, NavigationIconDescription> entry,
//                                   @StringRes int titleResId, @DrawableRes int activeResId, @DrawableRes int inactiveResId) {
//        NavigationIconDescription iconDescription = entry.getValue();
//        Map<String, String> iconMap = iconDescription.getIcon();
//
//        Drawable activeDrawable = null;
//        Drawable inactiveDrawable = null;
//        String title = null;
//
//        for (Map.Entry<String, String> iconEntry : iconMap.entrySet()) {
//            String iconFilePath = filePath + entry.getKey() + "_" + iconEntry.getKey() + ".png";
//            File iconFile = new File(iconFilePath);
//            if (!iconFile.exists()) {
//                break;
//            }
//            switch (iconEntry.getKey()) {
//                case "active":
//                    activeDrawable = Drawable.createFromPath(iconFilePath);
//                    break;
//                case "inactive":
//                    inactiveDrawable = Drawable.createFromPath(iconFilePath);
//                    break;
//            }
//
//        }
//
//        if (activeDrawable != null && inactiveDrawable != null) {
//            title = iconDescription.getName();
//        } else {
//            title = getString(titleResId);
//            activeDrawable = getResources().getDrawable(activeResId);
//            inactiveDrawable = getResources().getDrawable(inactiveResId);
//        }
//        bottomNavigationBar.addItem(new BottomNavigationItem(activeDrawable, title)
//                .setInactiveIcon(inactiveDrawable));
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        int targetPosition = intent.getIntExtra(IntentKeyConstants.TARGET_POSITION, 0);
//        bottomNavigationBar.selectTab(targetPosition);
//        dispatchScheme(intent);
//    }


    @Override
    public void onTabSelected(int position) {
//        if (currentPosition == 1 && position == 1 && hasChangePromotion) {
//            return;
//        }
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
