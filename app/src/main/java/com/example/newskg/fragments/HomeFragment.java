package com.example.newskg.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
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


public class HomeFragment extends Fragment {

RecyclerView recyclerViewHome;
private NewsAdapter newsAdapter;
private List<Article>list;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerViewHome = getActivity().findViewById(R.id.recyclerHome);
        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(list,getActivity());
        Date date = new Date();
       String data = String.valueOf(date.getTime());
        recyclerViewHome.setAdapter(newsAdapter);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        RetrofitBuilder.getService().getAllNews("general",data,"popularity","fdc952d04aa64ed4be22bd6c1e870552").enqueue(new Callback<News>() {
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