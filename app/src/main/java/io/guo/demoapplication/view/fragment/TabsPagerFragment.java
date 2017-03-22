package io.guo.demoapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.TabsComponent;
import io.guo.demoapplication.injection.HasComponent;
import io.guo.demoapplication.view.adapter.TabsPagerAdapter;

public class TabsPagerFragment extends Fragment {

    public static final String TAG = "TabsPagerFragment";

    @BindView(R.id.storages_pager)
    ViewPager pager;

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    private TabsPagerAdapter adapter;

    public static TabsPagerFragment newInstance() {
        TabsPagerFragment fragment = new TabsPagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(
            @Nullable
                    Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        return view;
    }

    @Override
    @DebugLog
    public void onViewCreated(View view,
                              @Nullable
                                      Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    @DebugLog
    public void onActivityCreated(
            @Nullable
                    Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (pager.getAdapter() == null) {
            TabsComponent component = ((HasComponent<TabsComponent>) getActivity())
                    .getComponent();
            adapter = new TabsPagerAdapter(getChildFragmentManager(), component);
            pager.setAdapter(adapter);
            tabLayout.setupWithViewPager(pager);
            pager.setCurrentItem(0);
        }
    }

    @DebugLog
    public void updateFragments() {
        adapter.updateFragments();
    }
}
