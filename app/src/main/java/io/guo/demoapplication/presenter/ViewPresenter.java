package io.guo.demoapplication.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ViewPresenter<V> {
    private V view;
    private String activityName;

    /**
     * Called to give this presenter control of a view
     */
    public final void takeView(
            @NonNull
            V view, @NonNull
                    String activityName) {
        this.view = view;
        this.activityName = activityName;
        onViewTaken(view);
    }

    public String getActivityName() {
        return activityName;
    }

    /**
     * Called to surrender control of this view, e.g. when the view is detached.
     */
    public final void dropView() {
        onViewDropped(view);
        this.view = null;
    }

    /**
     * Returns the view managed by this presenter, or null if {@link #takeView} has never been
     * called, or after {@link #dropView}.
     *
     * @return the current attached view or null if none
     */
    @Nullable
    protected final V getView() {
        return view;
    }

    /**
     * Called once the view is attached to this presenter
     */
    protected void onViewTaken(V view) {
    }

    /**
     * Called once the view is removed from this presenter
     */
    protected void onViewDropped(V view) {
    }


}
