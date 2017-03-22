package io.guo.demoapplication.presenter;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.TabOneView;

public class TabOnePresenter extends ViewPresenter<TabOneView> {

    private final DemoApplication application;

    public TabOnePresenter(DemoApplication application) {
        super();
        this.application = application;
        informTabOneReady();
    }

    @DebugLog
    @Override
    protected void onViewTaken(TabOneView view) {
        super.onViewTaken(view);
    }

    private void informTabOneReady() {
        TabOneView view = getView();
        if (view != null) {
            view.informTabOneReady();
        }
    }

    @DebugLog
    @Override
    protected void onViewDropped(TabOneView view) {
        super.onViewDropped(view);
    }

}
