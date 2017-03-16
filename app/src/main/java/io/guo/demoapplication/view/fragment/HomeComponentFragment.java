package io.guo.demoapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.HomeComponent;
import io.guo.demoapplication.injection.HasComponent;

public class HomeComponentFragment extends Fragment implements HasComponent<HomeComponent> {

    private HomeComponent component;
    public static final String TAG = "HomeComponentFragment";

    @Deprecated
    public HomeComponentFragment(){}

    @NonNull
    public static HomeComponentFragment newInstance() {
        return new HomeComponentFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (component == null) {
            component = HomeComponent.Initializer.init(DemoApplication.getComponent());
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
    public HomeComponent getComponent() {
        if (component == null) {
            throw new IllegalStateException("onAttach has not been called yet. Please move your " +
                    "call after Activity.onStart() has been called or if in a fragment it's " +
                    "recommend to do it after Fragment.onActivityCreated()");
        }
        return component;
    }

}
