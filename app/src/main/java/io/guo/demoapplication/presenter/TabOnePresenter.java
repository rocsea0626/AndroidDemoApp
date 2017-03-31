package io.guo.demoapplication.presenter;

import java.util.concurrent.TimeUnit;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.view.TabOneView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TabOnePresenter extends ViewPresenter<TabOneView> {

    private final DemoApplication application;
    private DisposableObserver<Long> disposable;

    public TabOnePresenter(DemoApplication application) {
        super();
        this.application = application;
    }

    @DebugLog
    @Override
    protected void onViewTaken(TabOneView view) {
        super.onViewTaken(view);
        useObservableInterval();
    }

    private void useObservableInterval() {
        disposable = Observable.interval(2, TimeUnit.SECONDS).take(10,
                TimeUnit.MILLISECONDS).subscribeOn
                (Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Long>() {

            @Override
            public void onNext(Long timeStamp) {
                informTimeStamp(timeStamp);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                informTabOneReady();
            }
        });
    }

    private void informTimeStamp(long timeStamp) {
        TabOneView view = getView();
        if (view != null) {
            view.informTimeStamp(timeStamp);
        }
    }

    private void informTabOneReady() {
        TabOneView view = getView();
        if (view != null) {
            view.informViewReady();
        }
    }

    @DebugLog
    @Override
    protected void onViewDropped(TabOneView view) {
        super.onViewDropped(view);
    }

}
