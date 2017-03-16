package io.guo.demoapplication;

import dagger.Component;
import io.guo.demoapplication.injection.module.SplashModule;
import io.guo.demoapplication.injection.scope.SplashScope;
import io.guo.demoapplication.view.activity.SplashActivity;


@SplashScope
@Component(dependencies = ApplicationComponent.class, modules = SplashModule.class)
public interface SplashComponent {

    SplashActivity inject(SplashActivity activity);

    /**
     * An initializer that creates the graph from an application component.
     */
    final class Initializer {
        private Initializer() {
        } // No instances.

        public static SplashComponent init(ApplicationComponent applicationComponent) {
            return DaggerSplashComponent.builder()
                    .applicationComponent(applicationComponent)
                    .splashModule(new SplashModule()).build();
        }
    }
}
