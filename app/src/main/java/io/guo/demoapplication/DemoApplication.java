package io.guo.demoapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;

import javax.inject.Inject;

import timber.log.Timber;

public class DemoApplication extends Application {

    private static final String TAG = "DemoApplication";

    /**
     * There's only one component for the entire application so we can set it static so others
     * can access it directly.
     */
    private static ApplicationComponent component;
    private boolean isAdsRemoved = false;

    @Inject
    DemoApplication demoApplication;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.e("onCreate()");

        buildComponentAndInject();

        Timber.plant(new FirebaseCrashTree());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            StrictMode.enableDefaults();
        }
    }

    public class FirebaseCrashTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return;
            }

//            FirebaseCrash.logcat(priority, tag, message);
//            FirebaseCrash.report(t);
        }

    }

    public static ApplicationComponent getComponent() {
        return component;
    }

    public void setAdsRemoved(boolean purchased) {
        isAdsRemoved = purchased;
    }

    public boolean isAdsRemoved() {
        return isAdsRemoved;
    }

    public void buildComponentAndInject() {
        component = ApplicationComponent.Initializer.init(this);
        component.inject(this);
    }

}
