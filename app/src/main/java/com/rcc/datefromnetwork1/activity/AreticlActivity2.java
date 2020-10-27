package com.rcc.datefromnetwork1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.rcc.datefromnetwork1.Article;
import com.rcc.datefromnetwork1.ArticleRespones;
import com.rcc.datefromnetwork1.R;
import com.rcc.datefromnetwork1.adapter.ArticleAdapter;
import com.rcc.datefromnetwork1.model.NewsApp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AreticlActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
   private List<Article>articleList=new ArrayList<>();


Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areticl2);

        recyclerView=findViewById(R.id.rv_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        


     String domain=getIntent().getStringExtra("domain");


        retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        retrofit.create(NewsApp.class).getAllArticlesFromSource(domain,"23ba2d485967445e829ec6a90b645699")
                .enqueue(new Callback<ArticleRespones>() {

                    @Override
                    public void onResponse(Call<ArticleRespones> call, Response<ArticleRespones> response) {
                        ArticleRespones articleRespones=response.body();
                        articleList=articleRespones.getArticles();
                        adapter=new ArticleAdapter(AreticlActivity2.this,articleList);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<ArticleRespones> call, Throwable t) {

                    }
                });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu,menu);




        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }





/*
    public void search(View view) {



        String search=edtsearch.getText().toString();
        if (TextUtils.isEmpty(search)){
            edtsearch.setError("faild");
        }
       edtsearch.clearComposingText();


        retrofit.create(NewsApp.class).gettarticlequry(search,"23ba2d485967445e829ec6a90b645699")
        .enqueue(new Callback<ArticleRespones>() {
            @Override
            public void onResponse(Call<ArticleRespones> call, Response<ArticleRespones> response) {
                ArticleRespones articleRespones=response.body();
                adapter=new ArticleAdapter(AreticlActivity2.this,articleRespones.getArticles());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArticleRespones> call, Throwable t) {

            }
        });

 */
    }
