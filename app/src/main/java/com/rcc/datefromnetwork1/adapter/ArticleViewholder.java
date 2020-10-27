package com.rcc.datefromnetwork1.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcc.datefromnetwork1.R;

public class ArticleViewholder extends RecyclerView.ViewHolder {
    TextView txttitle,txtarthure,txtdat;
    ImageView imggg;

    public ArticleViewholder(@NonNull View itemView) {
        super(itemView);
        txttitle=itemView.findViewById(R.id.txt_title);
        imggg=itemView.findViewById(R.id.img_res);
        txtdat=itemView.findViewById(R.id.date);
        txtarthure=itemView.findViewById(R.id.txtarthu);
    }
}
