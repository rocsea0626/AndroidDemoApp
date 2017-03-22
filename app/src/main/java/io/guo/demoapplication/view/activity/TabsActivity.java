package io.guo.demoapplication.view.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.TabsComponent;
import io.guo.demoapplication.injection.HasComponent;
import io.guo.demoapplication.presenter.TabsPresenter;
import io.guo.demoapplication.view.TabsView;
import io.guo.demoapplication.view.fragment.TabsComponentFragment;
import io.guo.demoapplication.view.fragment.TabsPagerFragment;

public class TabsActivity extends AppCompatActivity implements HasComponent<TabsComponent>, TabsView{

    private static final String TAG = "TabsActivity";

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.nav_drawer_layout)
    DrawerLayout navDrawerLayout;

    private TabsComponentFragment componentFragment;
    private TabsPagerFragment tabsPagerFragment;

    private ActionBarDrawerToggle drawerToggle;

    @Inject
    TabsPresenter presenter;


    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(TAG);
        setupNavigationDrawer();

        FragmentManager fragmentManager = getSupportFragmentManager();
        componentFragment = (TabsComponentFragment) fragmentManager
                .findFragmentByTag(TabsComponentFragment.TAG);

        // If the Fragment is non-null, then it is currently being retained across a
        // configuration change. Otherwise create it for the first time.
        if (componentFragment == null) {
            componentFragment = TabsComponentFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(componentFragment, TabsComponentFragment.TAG).commit();
        }

        if (savedInstanceState == null) {
            tabsPagerFragment = TabsPagerFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.content_frame, tabsPagerFragment, TabsPagerFragment.TAG)
                    .commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        componentFragment.getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.takeView(this, getLocalClassName());
    }

    @Override
    protected void onPause() {
        presenter.dropView();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
    public TabsComponent getComponent() {
        return componentFragment.getComponent();
    }

    @Override
    public void informTabsViewReady() {

    }

    @Override
    public void informActivity(ActivityInfo activityInfo) {

    }

}
