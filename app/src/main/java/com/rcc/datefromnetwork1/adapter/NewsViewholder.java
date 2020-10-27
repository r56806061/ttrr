package com.rcc.datefromnetwork1.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcc.datefromnetwork1.R;

public class NewsViewholder extends RecyclerView.ViewHolder {
    TextView txtnews;
    public NewsViewholder(@NonNull View itemView) {
        super(itemView);
        txtnews=itemView.findViewById(R.id.txtnews);

    }
}
