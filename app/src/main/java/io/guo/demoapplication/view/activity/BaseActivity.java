package io.guo.demoapplication.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.data.NavigationListMenuItem;
import io.guo.demoapplication.view.adapter.NavigationMenuListAdapter;

public abstract class BaseActivity extends AppCompatActivity implements NavigationMenuListAdapter
        .ListMenuItemClickedListener {

    private static final String TAG = "BaseActivity";

    private NavigationMenuListAdapter adapter;
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.nav_drawer_layout)
    DrawerLayout navDrawerLayout;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NavigationMenuListAdapter(this);
        setupNavigationDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupNavigationDrawer() {
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

    @Override
    public void onListMenuItemClicked(NavigationListMenuItem listMenuItem) {

    }
}
