package com.rcc.datefromnetwork1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.rcc.datefromnetwork1.R;
import com.rcc.datefromnetwork1.adapter.NewsAdapter;
import com.rcc.datefromnetwork1.model.NewsApp;
import com.rcc.datefromnetwork1.server.NewsRespones;
import com.rcc.datefromnetwork1.server.Source;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<Source>sourceList=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(NewsApp.class).getAllnews("23ba2d485967445e829ec6a90b645699")
                .enqueue(new Callback<NewsRespones>() {
                    @Override
                    public void onResponse(Call<NewsRespones> call, Response<NewsRespones> response) {
                        NewsRespones newsRespones=response.body();
                        sourceList=newsRespones.getSources();
                        adapter=new NewsAdapter(MainActivity.this,sourceList);
                        recyclerView.setAdapter(adapter);


                    }

                    @Override
                    public void onFailure(Call<NewsRespones> call, Throwable t) {
                        Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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
}

