package io.guo.demoapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

public class TabsActivity extends BaseActivity implements HasComponent<TabsComponent>,
        TabsView {

    public static final String TAG = "TabsActivity";
    private static final String CALLING_CLASS_NAME_ARG = "CALLING_CLASS_NAME_ARG";

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.nav_drawer_layout)
    DrawerLayout navDrawerLayout;

    @BindView(R.id.nav_list_menu)
    RecyclerView rcMenuList;

    private TabsComponentFragment componentFragment;
    private TabsPagerFragment tabsPagerFragment;

    @Inject
    TabsPresenter presenter;

    public static Intent newStartIntent(Context context,
                                        String callingClassName) {
        Intent intent = new Intent(context, TabsActivity.class);
        intent.putExtra(CALLING_CLASS_NAME_ARG, callingClassName);
        return intent;
    }


    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(TAG);
        setupNavigationDrawer(navDrawerLayout, toolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcMenuList.setLayoutManager(layoutManager);
        rcMenuList.setAdapter(adapter);

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
    @Override
    public TabsComponent getComponent() {
        return componentFragment.getComponent();
    }

}
