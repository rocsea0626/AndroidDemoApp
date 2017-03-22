package io.guo.demoapplication;

import dagger.Component;
import io.guo.demoapplication.injection.module.TabsModule;
import io.guo.demoapplication.injection.scope.TabsScope;
import io.guo.demoapplication.view.activity.TabsActivity;
import io.guo.demoapplication.view.adapter.TabsPagerAdapter;
import io.guo.demoapplication.view.fragment.TabOneFragment;


@TabsScope
@Component(dependencies = ApplicationComponent.class, modules = TabsModule.class)
public interface TabsComponent extends TabsPagerAdapter.Injector, TabOneFragment.Injector {

    TabsActivity inject(TabsActivity activity);

    /**
     * An initializer that creates the graph from an application component.
     */
    final class Initializer {
        private Initializer() {
        } // No instances.

        public static TabsComponent init(ApplicationComponent applicationComponent) {
            return DaggerTabsComponent.builder()
                    .applicationComponent(applicationComponent)
                    .tabsModule(new TabsModule()).build();
        }
    }
}
