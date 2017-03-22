package io.guo.demoapplication.view.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.guo.demoapplication.data.NavigationListMenuItem;
import io.guo.demoapplication.view.BaseActivityView;
import io.guo.demoapplication.view.adapter.NavigationMenuListAdapter;

public class BaseActivity extends AppCompatActivity implements NavigationMenuListAdapter
        .ListMenuItemClickedListener, BaseActivityView {

    protected NavigationMenuListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NavigationMenuListAdapter(this);
    }

    @Override
    public void onListMenuItemClicked(NavigationListMenuItem listMenuItem) {

    }

    @Override
    public void informActivityReady() {

    }

    @Override
    public void informActivity(ActivityInfo activityInfo) {
        NavigationListMenuItem item = new NavigationListMenuItem(activityInfo, 0);
        adapter.add(item);
    }

    @Override
    public void clearActivityDataset() {
        adapter.clear();
    }
}
