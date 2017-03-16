package io.guo.demoapplication.injection.module;

import dagger.Module;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.presenter.SplashPresenter;

@Module
public class SplashModule {

    SplashPresenter splashPresenter(DemoApplication application) {
        return new SplashPresenter(application);
    }

}
