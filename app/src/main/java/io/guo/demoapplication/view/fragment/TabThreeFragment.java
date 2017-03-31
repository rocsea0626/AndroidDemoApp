package io.guo.demoapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.injection.HasComponent;
import io.guo.demoapplication.presenter.TabOnePresenter;
import io.guo.demoapplication.view.TabOneView;

public class TabThreeFragment extends Fragment implements TabOneView {

    public static final String TAG = "TabThreeFragment";

    @BindView(R.id.title_tab)
    TextView tvTitle;

    @Inject
    TabOnePresenter presenter;

    public static Fragment newInstance() {
        TabThreeFragment fragment = new TabThreeFragment();
        return fragment;
    }

    @DebugLog
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @DebugLog
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HasComponent<Injector>) getActivity()).getComponent().inject(this);
    }

    @DebugLog
    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this, TAG);
    }

    @DebugLog
    @Override
    public void onPause() {
        presenter.dropView();
        super.onPause();
    }

    @Override
    public void clearDataSet() {

    }

    @Override
    public void informTimeStamp(long timeStamp) {

    }

    @Override
    public void informViewReady() {
        tvTitle.setText(TAG + " is ready");
    }

    public interface Injector {
        TabThreeFragment inject(TabThreeFragment fragment);
    }

}
