package com.example.newskg.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.newskg.R;
import com.example.newskg.adadpter.NewsAdapter;
import com.example.newskg.data.remote.RetrofitBuilder;
import com.example.newskg.pojo.Article;
import com.example.newskg.pojo.News;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScienceFragment extends Fragment {

    RecyclerView recyclerViewScience;
  private   NewsAdapter newsAdapter;
  private   List<Article> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_science, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    recyclerViewScience = getActivity().findViewById(R.id.recyclerScience);
        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(list,getActivity());
        Date date = new Date();
        String data = String.valueOf(date.getTime());
        recyclerViewScience.setAdapter(newsAdapter);
        recyclerViewScience.setLayoutManager(new LinearLayoutManager(getActivity()));
        RetrofitBuilder.getService().getAllNews("science",data,"popularity","fdc952d04aa64ed4be22bd6c1e870552").enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body() !=  null){
                    newsAdapter.add(response.body().getArticles());
                    newsAdapter.notifyDataSetChanged();}
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
