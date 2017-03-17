package io.guo.demoapplication.data;

import android.content.pm.ActivityInfo;
import android.support.annotation.DrawableRes;

public class NavigationListMenuItem {
    private ActivityInfo activityInfo;
    private final int iconDrawable;

    public NavigationListMenuItem(ActivityInfo activityInfo, @DrawableRes int iconDrawable) {
        this.activityInfo = activityInfo;
        this.iconDrawable = iconDrawable;
    }

    public ActivityInfo getActivityInfo() {
        return activityInfo;
    }

    public int getIconDrawable() {
        return iconDrawable;
    }
}
