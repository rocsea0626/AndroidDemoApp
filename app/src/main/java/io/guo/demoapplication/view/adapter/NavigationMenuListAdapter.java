package io.guo.demoapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;
import io.guo.demoapplication.data.NavigationListMenuItem;

public class NavigationMenuListAdapter extends RecyclerView.Adapter<NavigationMenuListAdapter
        .ViewHolder> {

    private final List<NavigationListMenuItem> data = new LinkedList<>();
    private ListMenuItemClickedListener listener;

    public interface ListMenuItemClickedListener {
        void onListMenuItemClicked(NavigationListMenuItem listMenuItem);
    }

    public NavigationMenuListAdapter(ListMenuItemClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_two_line_left_img,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        NavigationListMenuItem item = data.get(position);
        String[] tokens = item.getActivityInfo().name.split("\\.");
        viewHolder.title.setText(tokens[tokens.length - 1]);
        viewHolder.subtitle.setText(item.getActivityInfo().targetActivity);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @DebugLog
    public void add(NavigationListMenuItem listMenuItem) {
        data.add(listMenuItem);
        notifyItemInserted(data.size() - 1);
    }

    public void clear() {
        int itemCount = data.size();
        data.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public NavigationListMenuItem getItem(int position) {
        return data.get(position);
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        ImageView icon;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.subtitle)
        TextView subtitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
