package io.guo.demoapplication;

import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import io.guo.demoapplication.injection.module.ApplicationModule;
import io.guo.demoapplication.utils.LocalMusicManager;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    DemoApplication inject(DemoApplication application);

    // Expose application to other components
    DemoApplication application();

    SharedPreferences sharedPreferences();

    LocalMusicManager localMusicManager();

    EventBus eventBus();

    /**
     * An initializer that creates the graph from an application.
     */
    final class Initializer {
        private Initializer() {
        } // No instances.

        public static ApplicationComponent init(DemoApplication app) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app)).build();
        }
    }
}