package com.animrift.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animrift.app.R;
import com.animrift.app.db.AnimeEntity;
import com.animrift.app.ui.PlayerActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private final List<AnimeEntity> animeList;

    public AnimeAdapter(List<AnimeEntity> animeList) {
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        AnimeEntity model = animeList.get(position);
        holder.title.setText(model.getTitle());
        holder.episode.setText(model.getEpisodeNumber());
        holder.status.setText(model.getStatus());

        Glide.with(holder.itemView.getContext())
                .load(model.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.bg_input)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("video_url", model.getVideoUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animeList == null ? 0 : animeList.size();
    }

    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView episode;
        TextView status;

        AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageAnime);
            title = itemView.findViewById(R.id.textAnimeTitle);
            episode = itemView.findViewById(R.id.textEpisode);
            status = itemView.findViewById(R.id.textStatus);
        }
    }
}
