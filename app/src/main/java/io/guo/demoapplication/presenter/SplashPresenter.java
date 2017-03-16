package io.guo.demoapplication.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.SplashView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter extends ViewPresenter<SplashView> {

    private final DemoApplication application;

    @Inject
    public SplashPresenter(DemoApplication application) {
        super();
        this.application = application;
    }

    @DebugLog
    @Override
    protected void onViewTaken(SplashView view) {
        super.onViewTaken(view);
        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                informLaunchHomeActivity();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long timeElapsed) {
            }
        });
    }

    private void informLaunchHomeActivity() {
        SplashView view = getView();
        if (view != null) {
            view.informLaunchHomeActivity();
        }
    }

    @DebugLog
    @Override
    protected void onViewDropped(SplashView view) {
        super.onViewDropped(view);
    }

}
