package io.guo.demoapplication.view;

import android.content.pm.ActivityInfo;

public interface HomeView extends ContentView {

    void informHomeViewReady();

    void informActivity(ActivityInfo activityInfo);
}
