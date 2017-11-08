package com.example.raldoron.firebasetestapp.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Raldoron on 30.05.17.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    public ImageView headerImageView;

    public HeaderViewHolder(View headerView) {
        super(headerView);
        headerImageView = (ImageView) headerView;
    }
}
