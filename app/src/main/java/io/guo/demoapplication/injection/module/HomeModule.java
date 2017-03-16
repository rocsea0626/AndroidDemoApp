package io.guo.demoapplication.injection.module;

import dagger.Module;
import dagger.Provides;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.presenter.HomePresenter;

@Module
public class HomeModule {

    @Provides
    HomePresenter homePresenter(DemoApplication application) {
        return new HomePresenter(application);
    }

}
