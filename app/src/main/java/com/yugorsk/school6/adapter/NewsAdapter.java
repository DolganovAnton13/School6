package com.yugorsk.school6.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yugorsk.school6.data.News;
import com.yugorsk.school6.databinding.ItemNewsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<News> listNews;
    private NewsClickListener newsClickListener;

    public interface NewsClickListener {
        void onNewsClick(News news);
    }

    public NewsAdapter() {
        this.listNews = new ArrayList<>();
    }

    public void setNewsClickListener(NewsClickListener newsClickListener) {
        this.newsClickListener = newsClickListener;
    }

    public void setNews(List<News> newsUpdate) {
        if (getItemCount() == 0) {
            listNews = newsUpdate;
            notifyItemRangeInserted(0, newsUpdate.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return listNews.size();
                }

                @Override
                public int getNewListSize() {
                    return newsUpdate.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(listNews.get(oldItemPosition).getId(), newsUpdate.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    News newNews = newsUpdate.get(newItemPosition);
                    News oldNews = listNews.get(oldItemPosition);
                    return Objects.equals(newNews.getId(), oldNews.getId())
                            && Objects.equals(newNews.getDescription(), oldNews.getDescription());
                }
            });
            listNews = newsUpdate;
            result.dispatchUpdatesTo(this);
        }
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemNewsBinding binding;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            newsClickListener.onNewsClick(binding.getNews());
        }
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding binding = ItemNewsBinding.inflate(inflater, parent, false);
        return new NewsHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.binding.setNews(listNews.get(position));
    }

    @Override
    public int getItemCount() {
        return listNews == null ? 0 : listNews.size();
    }
}
