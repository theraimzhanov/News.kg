package com.example.newskg.data.remote;

import com.example.newskg.pojo.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
@GET("v2/everything")
    Call<News>getAllNews(@Query("q")String tesla, @Query("from")String data, @Query("sortBy")String sortBy, @Query("apiKey")String apiKe);
}
//GET https://newsapi.org/v2/everything?q=apple&from=2021-11-24&to=2021-11-24&sortBy=popularity&apiKey=fdc952d04aa64ed4be22bd6c1e870552