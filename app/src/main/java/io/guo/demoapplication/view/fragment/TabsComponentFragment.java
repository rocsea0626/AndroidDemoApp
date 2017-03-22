package io.guo.demoapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import io.guo.demoapplication.DemoApplication;
import io.guo.demoapplication.TabsComponent;
import io.guo.demoapplication.injection.HasComponent;

public class TabsComponentFragment extends Fragment implements HasComponent<TabsComponent> {

    private TabsComponent component;
    public static final String TAG = "TabsComponentFragment";

    @Deprecated
    public TabsComponentFragment(){}

    @NonNull
    public static TabsComponentFragment newInstance() {
        return new TabsComponentFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (component == null) {
            component = TabsComponent.Initializer.init(DemoApplication.getComponent());
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
    public TabsComponent getComponent() {
        if (component == null) {
            throw new IllegalStateException("onAttach has not been called yet. Please move your " +
                    "call after Activity.onStart() has been called or if in a fragment it's " +
                    "recommend to do it after Fragment.onActivityCreated()");
        }
        return component;
    }

}
