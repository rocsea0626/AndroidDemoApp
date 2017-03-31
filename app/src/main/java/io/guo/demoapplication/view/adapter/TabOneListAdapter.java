package io.guo.demoapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.guo.demoapplication.R;

public class TabOneListAdapter extends RecyclerView.Adapter<TabOneListAdapter
        .ViewHolder> {

    private final List<Long> data = new LinkedList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_single_text,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        long item = data.get(position);

        viewHolder.title.setText(Long.toString(item));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @DebugLog
    public void add(long timeStampe) {
        data.add(timeStampe);
        notifyItemInserted(data.size() - 1);
    }

    public void clear() {
        int itemCount = data.size();
        data.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public long getItem(int position) {
        return data.get(position);
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
