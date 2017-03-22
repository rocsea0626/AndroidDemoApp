package io.guo.demoapplication.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.TabsComponent;
import io.guo.demoapplication.model.TabsPagerFragmentModel;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    @Inject
    TabsPagerFragmentModel fragmentModel;

    @Inject
    DemoApplication application;

    public TabsPagerAdapter(FragmentManager manager, TabsComponent component) {
        super(manager);
        component.inject(this);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentModel.getItem(position);
    }

    @Override
    public int getCount() {
        return fragmentModel.getCount();
    }

    @DebugLog
    @Override
    public CharSequence getPageTitle(int position) {
        return application.getString(fragmentModel.getPageTitle(position));
    }

    public void updateFragments() {
        notifyDataSetChanged();
    }

    @DebugLog
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public interface Injector {
        void inject(TabsPagerAdapter adapter);
    }
}
