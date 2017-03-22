package io.guo.demoapplication.view.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.data.NavigationListMenuItem;
import io.guo.demoapplication.view.BaseActivityView;
import io.guo.demoapplication.view.adapter.NavigationMenuListAdapter;

public class BaseActivity extends AppCompatActivity implements NavigationMenuListAdapter
        .ListMenuItemClickedListener, BaseActivityView {

    private static final String TAG = "BaseActivity";

    protected NavigationMenuListAdapter adapter;
    protected ActionBarDrawerToggle drawerToggle;

    @DebugLog
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NavigationMenuListAdapter(this);
    }

    @Override
    public void onListMenuItemClicked(NavigationListMenuItem listMenuItem) {

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat
                .makeCustomAnimation(this, R.anim.anim_slide_left_right, R.anim
                        .anim_slide_right_left);

        Intent intent = null;

        if (listMenuItem.getActivityInfo().name.contains(TabsActivity.TAG)) {
            intent = TabsActivity.newStartIntent(this, BaseActivity.TAG);
        }
        if (listMenuItem.getActivityInfo().name.contains(HomeActivity.TAG)) {
            intent = new Intent(this, HomeActivity.class);
        }

        if(intent != null){
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        }
    }

    @Override
    public void informActivityReady() {

    }

    @DebugLog
    @Override
    public void informActivity(ActivityInfo activityInfo) {
        if (!activityInfo.name.contains(SplashActivity.TAG)) {
            NavigationListMenuItem item = new NavigationListMenuItem(activityInfo, 0);
            adapter.add(item);
        }
    }

    @Override
    public void clearActivityDataset() {
        adapter.clear();
    }

    protected void setupNavigationDrawer(DrawerLayout navDrawerLayout, Toolbar toolbar){
        drawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout, toolbar, R.string
                .open_nav_drawer, R.string.close_nav_drawer) {

            @DebugLog
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @DebugLog
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };
        navDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
}
