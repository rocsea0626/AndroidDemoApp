package io.guo.demoapplication.view;

import android.content.pm.ActivityInfo;

public interface BaseActivityView {

    void informActivityReady();

    void informActivity(ActivityInfo activityInfo);

    void clearActivityDataset();
}
