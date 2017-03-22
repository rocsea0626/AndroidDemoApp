package io.guo.demoapplication.injection.module;

import dagger.Module;
import dagger.Provides;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.injection.scope.TabsScope;
import io.guo.demoapplication.model.TabsPagerFragmentModel;
import io.guo.demoapplication.model.TabsPagerFragmentModelImpl;
import io.guo.demoapplication.presenter.TabOnePresenter;
import io.guo.demoapplication.presenter.TabsPresenter;

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

}
