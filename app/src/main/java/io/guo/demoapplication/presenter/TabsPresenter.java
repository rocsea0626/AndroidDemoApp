package io.guo.demoapplication.presenter;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.TabsView;
import timber.log.Timber;

public class TabsPresenter extends ViewPresenter<TabsView> {

    private final DemoApplication application;

    public TabsPresenter(DemoApplication application) {
        super();
        this.application = application;
    }

    @DebugLog
    @Override
    protected void onViewTaken(TabsView view) {
        super.onViewTaken(view);
        prepareNavigationMenuList();
        informLaunchHomeActivity();
    }

    private void prepareNavigationMenuList() {
        try {
            ActivityInfo[] list = application.getPackageManager().getPackageInfo(application
                    .getPackageName(),
                    PackageManager.GET_ACTIVITIES).activities;
            for (ActivityInfo ai : list) {
                informActivity(ai);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Timber.e(e);
            informError();
        }
    }

    private void informActivity(ActivityInfo activityInfo) {
        TabsView view = getView();
        if (view != null) {
            view.informActivity(activityInfo);
        }
    }

    private void informError() {

    }

    private void informLaunchHomeActivity() {
        TabsView view = getView();
        if (view != null) {
            view.informActivityReady();
        }
    }

    @DebugLog
    @Override
    protected void onViewDropped(TabsView view) {
        super.onViewDropped(view);
    }

}
