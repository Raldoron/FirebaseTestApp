package com.example.raldoron.firebasetestapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Raldoron on 30.05.17.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ItemViewHolder(View itemView){
        super(itemView);
        textView = (TextView) itemView;
    }
}
