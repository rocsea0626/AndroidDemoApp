package io.guo.demoapplication.injection.module;

import dagger.Module;
import dagger.Provides;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.injection.scope.TabsScope;
import io.guo.demoapplication.model.TabTwoModel;
import io.guo.demoapplication.model.TabTwoModelImpl;
import io.guo.demoapplication.model.TabsModel;
import io.guo.demoapplication.model.TabsModelImpl;
import io.guo.demoapplication.model.TabsPagerFragmentModel;
import io.guo.demoapplication.model.TabsPagerFragmentModelImpl;
import io.guo.demoapplication.presenter.TabOnePresenter;
import io.guo.demoapplication.presenter.TabTwoPresenter;
import io.guo.demoapplication.presenter.TabsPresenter;
import io.guo.demoapplication.utils.LocalMusicManager;
import io.guo.demoapplication.view.adapter.TabOneListAdapter;
import io.guo.demoapplication.view.adapter.TabTwoListAdapter;

@Module
public class TabsModule {

    @Provides
    TabsPresenter tabsPresenter(DemoApplication application) {
        return new TabsPresenter(application);
    }

    @Provides
    TabOnePresenter tabOnePresenter(DemoApplication application) {
        return new TabOnePresenter(application);
    }

    @Provides
    TabTwoPresenter tabTwoPresenter(DemoApplication application, TabTwoModel tabTwoModel) {
        return new TabTwoPresenter(application, tabTwoModel);
    }

    /**
     * Creates the right {@link TabsPagerFragmentModel} depending on the current screen.
     * This will be used in {@link io.guo.demoapplication.view.adapter.TabsPagerAdapter}'s pager adapter
     * to decide what fragments to show.
     *
     * @return a {@link TabsPagerFragmentModel} implementation based on source and media types.
     */
    @TabsScope
    @Provides
    TabsPagerFragmentModel tabsPagerFragmentModel() {
        return new TabsPagerFragmentModelImpl();
    }

    @TabsScope
    @Provides
    TabsModel tabsModel() {
        return new TabsModelImpl();
    }

    @TabsScope
    @Provides
    TabTwoModel tabTwoModel(LocalMusicManager localMusicManager) {
        return new TabTwoModelImpl(localMusicManager);
    }

    @TabsScope
    @Provides
    TabOneListAdapter tabOneListAdapter() {
        return new TabOneListAdapter();
    }

    @TabsScope
    @Provides
    TabTwoListAdapter tabTwoListAdapter() {
        return new TabTwoListAdapter();
    }

}
