package io.guo.demoapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.SplashComponent;
import io.guo.demoapplication.injection.HasComponent;

public class SplashComponentFragment extends Fragment implements HasComponent<SplashComponent> {

    private SplashComponent component;
    public static final String TAG = "SplashComponentFragment";

    @Deprecated
    public SplashComponentFragment(){}

    @NonNull
    public static SplashComponentFragment newInstance() {
        return new SplashComponentFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (component == null) {
            component = SplashComponent.Initializer.init(DemoApplication.getComponent());
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public SplashComponent getComponent() {
        if (component == null) {
            throw new IllegalStateException("onAttach has not been called yet. Please move your " +
                    "call after Activity.onStart() has been called or if in a fragment it's " +
                    "recommend to do it after Fragment.onActivityCreated()");
        }
        return component;
    }

}
