package io.guo.demoapplication;

import dagger.Component;
import io.guo.demoapplication.injection.module.HomeModule;
import io.guo.demoapplication.injection.scope.HomeScope;
import io.guo.demoapplication.view.activity.HomeActivity;


@HomeScope
@Component(dependencies = ApplicationComponent.class, modules = HomeModule.class)
public interface HomeComponent {

    HomeActivity inject(HomeActivity activity);

    /**
     * An initializer that creates the graph from an application component.
     */
    final class Initializer {
        private Initializer() {
        } // No instances.

        public static HomeComponent init(ApplicationComponent applicationComponent) {
            return DaggerHomeComponent.builder()
                    .applicationComponent(applicationComponent)
                    .homeModule(new HomeModule()).build();
        }
    }
}
