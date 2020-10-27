package com.rcc.datefromnetwork1.model;

import com.rcc.datefromnetwork1.ArticleRespones;
import com.rcc.datefromnetwork1.server.NewsRespones;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApp {


   // https://newsapi.org/v2/sources?apiKey=23ba2d485967445e829ec6a90b645699

    @GET("sources")
    Call<NewsRespones>getAllnews(@Query("apiKey")String apikey);


    @GET("top-headlines")
    Call<ArticleRespones> getAllArticlesFromSource(@Query("sources") String sourceId,
                                                   @Query("apiKey") String apiKey);


//https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=23ba2d485967445e829ec6a90b645699


}
