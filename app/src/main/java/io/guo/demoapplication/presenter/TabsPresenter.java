package io.guo.demoapplication.presenter;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.TabsView;

public class TabsPresenter extends BasePresenter<TabsView> {

    public TabsPresenter(DemoApplication application) {
        super(application);
    }

    @DebugLog
    @Override
    protected void onViewTaken(TabsView view) {
        super.onViewTaken(view);
    }

    @DebugLog
    @Override
    protected void onViewDropped(TabsView view) {
        super.onViewDropped(view);
    }

}
