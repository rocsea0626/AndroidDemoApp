package io.guo.demoapplication.presenter;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(DemoApplication application) {
        super(application);
    }

    @DebugLog
    @Override
    protected void onViewTaken(HomeView view) {
        super.onViewTaken(view);
    }

    @DebugLog
    @Override
    protected void onViewDropped(HomeView view) {
        super.onViewDropped(view);
    }

}
