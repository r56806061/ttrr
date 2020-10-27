package com.rcc.datefromnetwork1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcc.datefromnetwork1.Article;
import com.rcc.datefromnetwork1.R;
import com.rcc.datefromnetwork1.activity.AreticlActivity2;
import com.rcc.datefromnetwork1.activity.MainActivity;
import com.rcc.datefromnetwork1.server.Source;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewholder> implements Filterable {
    private Context context;
    private List<Source>sourceList;
    private List<Source>sourceListfull;

    public NewsAdapter(Context context, List<Source> sourceList) {
        this.context = context;
        this.sourceList = sourceList;
        sourceListfull =new ArrayList<>(sourceList);
    }

    @NonNull
    @Override
    public NewsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_row_item,parent,false);
        NewsViewholder newsViewholder=new NewsViewholder(view);
        return newsViewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewholder holder, int position) {
        final Source cuurent=sourceList.get(position);
        holder.txtnews.setText(cuurent.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, AreticlActivity2.class);
                i.putExtra("domain",cuurent.getId());
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    @Override
    public Filter getFilter() {

        return videosFilterfull;
    }
    private Filter videosFilterfull = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Source> filteredVideos = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredVideos.addAll(sourceListfull);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < sourceListfull.size(); i++) {
                    if (sourceListfull.get(i).getLanguage().toLowerCase().contains(pattern)) {
                        filteredVideos.add(sourceListfull.get(i));
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredVideos;

            return results;



        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };
}
