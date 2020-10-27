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
import com.rcc.datefromnetwork1.Source;
import com.rcc.datefromnetwork1.activity.MainActivity2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewholder>implements Filterable {
    private Context context;
    private List<Article>articleList;
    private List<Article>articleListfull;

    public ArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
        articleListfull=new ArrayList<>(articleList);
    }

    @NonNull
    @Override
    public ArticleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.article_row_item,parent,false);
        ArticleViewholder viewholder=new ArticleViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewholder holder, int position) {

        final Article cuurentarticle=articleList.get(position);
        holder.txttitle.setText(cuurentarticle.getTitle());

        Picasso.get().load(cuurentarticle.getUrlToImage()).into(holder.imggg);

        holder.txtarthure.setText(cuurentarticle.getAuthor());
        holder.txtdat.setText(cuurentarticle.getPublishedAt());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,MainActivity2.class);
                i.putExtra("url",cuurentarticle.getUrl());
                context.startActivity(i);
            }
        });





    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @Override
    public Filter getFilter() {
        return videosFilter;
    }
    private Filter videosFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {



            List<Article> filteredVideos = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredVideos.addAll(articleListfull);
            }else {
                String pattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < articleListfull.size(); i++){
                    if (articleListfull.get(i).getTitle().toLowerCase().contains(pattern)){
                        filteredVideos.add(articleListfull.get(i));
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredVideos;

            return results;


        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            articleList.clear();
            articleList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}
