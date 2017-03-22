package io.guo.demoapplication.model;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;


/**
 * Handles getting different {@link io.guo.demoapplication.view.adapter.TabsPagerAdapter}
 * configurations. Each configuration
 * implements this model interface.
 */

public interface TabsPagerFragmentModel {

    int getCount();

    Fragment getItem(int position);

    @StringRes
    int getPageTitle(int position);
}
