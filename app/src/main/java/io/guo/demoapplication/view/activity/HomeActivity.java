package io.guo.demoapplication.view.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.HomeComponent;
import io.guo.demoapplication.R;
import io.guo.demoapplication.injection.HasComponent;
import io.guo.demoapplication.presenter.HomePresenter;
import io.guo.demoapplication.view.HomeView;
import io.guo.demoapplication.view.adapter.NavigationMenuListAdapter;
import io.guo.demoapplication.view.fragment.HomeComponentFragment;

public class HomeActivity extends BaseActivity implements HasComponent<HomeComponent>,
        HomeView, NavigationMenuListAdapter.ListMenuItemClickedListener {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.nav_drawer_layout)
    DrawerLayout navDrawerLayout;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.title_home_activity)
    TextView tvTitle;

    @BindView(R.id.nav_list_menu)
    RecyclerView rcMenuList;

    private ActionBarDrawerToggle drawerToggle;

    @Inject
    HomePresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;

    /**
     * Retained fragment to hold an instance of the {@link HomeComponent} across
     * activity recreations.
     */
    private HomeComponentFragment componentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(TAG);
        setupNavigationDrawer();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcMenuList.setLayoutManager(layoutManager);
        rcMenuList.setAdapter(adapter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        componentFragment = (HomeComponentFragment) fragmentManager
                .findFragmentByTag(HomeComponentFragment.TAG);

        // If the Fragment is non-null, then it is currently being retained across a
        // configuration change. Otherwise create it for the first time.
        if (componentFragment == null) {
            componentFragment = HomeComponentFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(componentFragment, HomeComponentFragment.TAG).commit();
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
    protected void onStop() {
        presenter.dropView();
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
    public HomeComponent getComponent() {
        return componentFragment.getComponent();
    }

    @Override
    public void informActivityReady() {
        super.informActivityReady();
        tvTitle.setText(TAG + " is ready");
    }

}
