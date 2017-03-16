package io.guo.demoapplication.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.BuildConfig;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.guo.demoapplication.DemoApplication;

@Module
public final class ApplicationModule {
    private final DemoApplication application;
    private static final String PREFS_DEFAULT = "guo_demo_apo";

    public ApplicationModule(DemoApplication application) {
        this.application = application;
    }

    @Provides
    DemoApplication application() {
        return application;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPrefs(DemoApplication app) {
        return app.getSharedPreferences(PREFS_DEFAULT, Context.MODE_PRIVATE);
    }

//    @Singleton
//    @Provides
//    DatabaseManager databaseManager() {
//        return DatabaseManager.getManager();
//    }

    @Singleton
    @Provides
    EventBus eventBus() {
        // We want to fail if a onEvent method throws exception in DEBUG mode only
        return EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).build();
    }

//    @Singleton
//    @Provides
//    DeviceInfoManager deviceInfoManager(DemoApplication application) {
//        return DeviceInfoManager.getManager(application);
//    }

}