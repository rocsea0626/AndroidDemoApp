package io.guo.demoapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.guo.demoapplication.R;
import io.guo.demoapplication.SplashComponent;
import io.guo.demoapplication.injection.HasComponent;
import io.guo.demoapplication.presenter.SplashPresenter;
import io.guo.demoapplication.view.SplashView;
import io.guo.demoapplication.view.fragment.SplashComponentFragment;


public class SplashActivity extends AppCompatActivity implements HasComponent<SplashComponent>,
        SplashView {

    public static final CharSequence TAG = "SplashActivity";
    @Inject
    SplashPresenter presenter;

    /**
     * Retained fragment to hold an instance of the {@link SplashComponent} across
     * activity recreations.
     */
    private SplashComponentFragment componentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        componentFragment = (SplashComponentFragment) fragmentManager
                .findFragmentByTag(SplashComponentFragment.TAG);

        // If the Fragment is non-null, then it is currently being retained across a
        // configuration change. Otherwise create it for the first time.
        if (componentFragment == null) {
            componentFragment = SplashComponentFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(componentFragment, SplashComponentFragment.TAG).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        componentFragment.getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.takeView(this, getLocalClassName());
    }

    @Override
    protected void onStop() {
        presenter.dropView();
        super.onStop();
    }

    @Override
    public void informLaunchHomeActivity() {
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat
                .makeCustomAnimation(this, R.anim.anim_slide_left_right, R.anim
                        .anim_slide_right_left);
        ActivityCompat.startActivity(this, new Intent(this, HomeActivity.class), activityOptions
                .toBundle());
        finish();
    }

    @Override
    public SplashComponent getComponent() {
        return componentFragment.getComponent();
    }
}
