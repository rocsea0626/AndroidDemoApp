package io.guo.demoapplication.presenter;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.BaseActivityView;
import timber.log.Timber;

public class BasePresenter<V extends BaseActivityView> extends ViewPresenter<V> {

    protected final DemoApplication application;

    public BasePresenter(DemoApplication application) {
        super();
        this.application = application;
    }

    @DebugLog
    @Override
    protected void onViewTaken(V view) {
        super.onViewTaken(view);
        prepareNavigationMenuList();
        informActivityReady();
    }

    @DebugLog
    @Override
    protected void onViewDropped(V view) {
        if (view != null) {
            view.clearActivityDataset();
        }
        super.onViewDropped(view);
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
        BaseActivityView view = getView();
        if (view != null) {
            view.informActivity(activityInfo);
        }
    }

    private void informError() {

    }

    private void informActivityReady() {
        BaseActivityView view = getView();
        if (view != null) {
            view.informActivityReady();
        }
    }

}
