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
import io.guo.demoapplication.R;
import io.guo.demoapplication.data.AudioInfo;

public class TabTwoListAdapter extends RecyclerView.Adapter<TabTwoListAdapter
        .ViewHolder> {

    private final List<AudioInfo> data = new LinkedList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_two_line_left_img,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AudioInfo item = data.get(position);

        viewHolder.title.setText(item.getTitle());
        viewHolder.subtitle.setText(item.getArtist());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void add(AudioInfo timeStampe) {
        data.add(timeStampe);
        notifyItemInserted(data.size() - 1);
    }

    public void clear() {
        int itemCount = data.size();
        data.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public AudioInfo getItem(int position) {
        return data.get(position);
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

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
