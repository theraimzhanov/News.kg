package com.example.newskg.adadpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newskg.R;
import com.example.newskg.pojo.Article;
import com.example.newskg.ui.WebViewActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<Article> articleList;
    Context context;

    public NewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news,parent,false);
        return new NewsViewHolder(view);
    }
    public void add(List<Article>newList){
        this.articleList = newList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(articleList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
         CircleImageView circleImageView;
         CardView cardView;
        TextView textTitleNews,textDescribtionNews,textAutor,textData;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textDescribtionNews = itemView.findViewById(R.id.newsContent);
            textTitleNews = itemView.findViewById(R.id.newsTitle);
            cardView = itemView.findViewById(R.id.cardView);
            circleImageView = itemView.findViewById(R.id.imageNews);
            textData = itemView.findViewById(R.id.newsPublishAt);
            textAutor=itemView.findViewById(R.id.textAutor);
        }

        public void bind(Article article) {

            Glide.with(context).load(article.getUrlToImage()).into(circleImageView);
            textTitleNews.setText(article.getTitle());
            textDescribtionNews.setText(article.getContent());
            textData.setText(String.valueOf(article.getPublishedAt()));
            if (article.getAuthor() == null){
                textAutor.setText("Не извезтный");
            }
            textAutor.setText(String.valueOf(article.getAuthor()));

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    String url = article.getUrl();
                    intent.putExtra("url",url);
                    context.startActivity(intent);
                }
            });
        }
    }
}

