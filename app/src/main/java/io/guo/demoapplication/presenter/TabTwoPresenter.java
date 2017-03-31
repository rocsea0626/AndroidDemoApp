package io.guo.demoapplication.presenter;

import java.util.List;

import hugo.weaving.DebugLog;
import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.data.AudioInfo;
import io.guo.demoapplication.model.TabTwoModel;
import io.guo.demoapplication.view.TabTwoView;
import io.reactivex.observers.DisposableObserver;

public class TabTwoPresenter extends ViewPresenter<TabTwoView> {

    private final DemoApplication application;
    private TabTwoModel tabTwoModel;
    private DisposableObserver<Long> disposable;

    public TabTwoPresenter(DemoApplication application, TabTwoModel tabTwoModel) {
        super();
        this.application = application;
        this.tabTwoModel = tabTwoModel;
    }

    @DebugLog
    @Override
    protected void onViewTaken(TabTwoView view) {
        super.onViewTaken(view);
        getLocalSongs();
    }

    //    private void useObservableInterval() {
//        disposable = Observable.interval(2, TimeUnit.SECONDS).take(10,
//                TimeUnit.MILLISECONDS).subscribeOn
//                (Schedulers.io()).observeOn
//                (AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Long>() {
//
//            @Override
//            public void onNext(Long timeStamp) {
//                informTimeStamp(timeStamp);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//                informTabOneReady();
//            }
//        });

    @DebugLog
    @Override
    protected void onViewDropped(TabTwoView view) {
        super.onViewDropped(view);
    }

    @DebugLog
    private void getLocalSongs() {
        List<AudioInfo> list = tabTwoModel.dicoverLocalSongs();
    }

//    }

    private void informTabTwoReady() {
        TabTwoView view = getView();
        if (view != null) {
            view.informViewReady();
        }
    }

}
