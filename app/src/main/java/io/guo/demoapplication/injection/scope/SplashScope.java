package io.guo.demoapplication.injection.scope;

import javax.inject.Scope;

import io.guo.demoapplication.view.activity.SplashActivity;

/**
 * Scope used inside {@link SplashActivity }.
 * Any provider annotated with it will provide one instance of the dependency per each
 * {@link io.guo.demoapplication.injection.module.SplashModule}
 */
// See http://stackoverflow.com/a/28206889 (and https://github.com/google/dagger/issues/107) for
// more details
@Scope
public @interface SplashScope {
}
