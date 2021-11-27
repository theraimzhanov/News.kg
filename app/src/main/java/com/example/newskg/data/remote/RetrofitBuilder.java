package com.example.newskg.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static RetrofitService service;

    public static RetrofitService getService() {
        if (service == null) {
            service =buildRetrofit();
        }
        return service;
    }

    private static RetrofitService buildRetrofit() {
        return new Retrofit.Builder().baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(RetrofitService.class);
    }
}
//GET https://newsapi.org/v2/everything?q=apple&from=2021-11-24&to=2021-11-24&sortBy=popularity&apiKey=fdc952d04aa64ed4be22bd6c1e870552