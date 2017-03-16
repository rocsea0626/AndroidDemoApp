package io.guo.demoapplication.presenter;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.HomeView;

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
        informLaunchHomeActivity();
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
