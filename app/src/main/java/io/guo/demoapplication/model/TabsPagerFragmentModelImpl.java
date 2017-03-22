package io.guo.demoapplication.model;

import android.support.v4.app.Fragment;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.view.fragment.TabOneFragment;
import io.guo.demoapplication.view.fragment.TabThreeFragment;
import io.guo.demoapplication.view.fragment.TabTwoFragment;

public class TabsPagerFragmentModelImpl implements TabsPagerFragmentModel {

    private static final int PAGE_TITLES[] = {R.string.title_tab_1, R.string.title_tab_2, R.string
            .title_tab_3};

    @Override
    public int getCount() {
        return PAGE_TITLES.length;
    }

    @DebugLog
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TabOneFragment.newInstance();
            case 1:
                return TabTwoFragment.newInstance();
            case 2:
                return TabThreeFragment.newInstance();
            default:
                throw new IllegalArgumentException("Invalid page position " + position);
        }
    }

    @Override
    public int getPageTitle(int position) {
        return PAGE_TITLES[position];
    }
}
