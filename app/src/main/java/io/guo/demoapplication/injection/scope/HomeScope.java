package io.guo.demoapplication.injection.scope;

import javax.inject.Scope;

/**
 * Scope used inside {@link io.guo.demoapplication.view.activity.HomeActivity }.
 * Any provider annotated with it will provide one instance of the dependency per each
 * {@link io.guo.demoapplication.injection.module.HomeModule}
 */
// See http://stackoverflow.com/a/28206889 (and https://github.com/google/dagger/issues/107) for
// more details
@Scope
public @interface HomeScope {
}
