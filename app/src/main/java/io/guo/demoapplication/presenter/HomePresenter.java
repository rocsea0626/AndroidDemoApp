package io.guo.demoapplication.presenter;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.HomeView;
import timber.log.Timber;

public class HomePresenter extends ViewPresenter<HomeView> {

    private final DemoApplication application;

    public HomePresenter(DemoApplication application) {
        super();
        this.application = application;
    }

    @DebugLog
    @Override
    protected void onViewTaken(HomeView view) {
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
        HomeView view = getView();
        if (view != null) {
            view.informActivity(activityInfo);
        }
    }

    private void informError() {

    }

    private void informLaunchHomeActivity() {
        HomeView view = getView();
        if (view != null) {
            view.informHomeViewReady();
        }
    }

    @DebugLog
    @Override
    protected void onViewDropped(HomeView view) {
        super.onViewDropped(view);
    }

}
