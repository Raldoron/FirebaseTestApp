package com.example.raldoron.firebasetestapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raldoron.firebasetestapp.ViewHolders.HeaderViewHolder;
import com.example.raldoron.firebasetestapp.ViewHolders.ItemViewHolder;

/**
 * Created by Raldoron on 16.04.17.
 */

public class NavigationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String[] items;
    private NavigationOnClickListener itemClickListener;

    public NavigationAdapter(String[] nav_items, NavigationOnClickListener listener){
        items = nav_items;
        itemClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isHeaderPosition(int position){
        return position == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.navigation_header, parent, false);
            final HeaderViewHolder headerViewHolder = new HeaderViewHolder(headerView);
            return headerViewHolder;
        }
        else if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.navigation_item, parent, false);
            final ItemViewHolder viewHolder = new ItemViewHolder(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
                }
            });
            return viewHolder;
        }
        throw new RuntimeException("Undefined view type for NavAdapter: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder header = (HeaderViewHolder) holder;
            header.headerImageView.setBackgroundResource(R.drawable.header);
        } else if (holder instanceof ItemViewHolder){
            ItemViewHolder item = (ItemViewHolder) holder;
            item.textView.setText(items[position - 1]);
        }

    }

    @Override
    public int getItemCount() {
        return items.length + 1;
    }

    public interface NavigationOnClickListener {
        void onItemClick(View view, int position);
    }
}
