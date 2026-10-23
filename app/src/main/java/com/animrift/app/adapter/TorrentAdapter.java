package com.animrift.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animrift.app.R;
import com.animrift.app.torrent.TorrentManager;
import com.animrift.app.torrent.TorrentModel;

import java.util.List;

public class TorrentAdapter extends RecyclerView.Adapter<TorrentAdapter.TorrentViewHolder> {

    private List<TorrentModel> items;

    public TorrentAdapter(List<TorrentModel> items) {
        this.items = items;
    }

    public void updateList(List<TorrentModel> newList) {
        this.items = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TorrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_torrent, parent, false);
        return new TorrentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TorrentViewHolder holder, int position) {
        TorrentModel item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.size.setText(item.getSize());
        holder.quality.setText(item.getQuality());
        holder.seeders.setText(item.getSeeders());

        holder.buttonDownload.setOnClickListener(v -> {
            Context context = v.getContext();
            TorrentManager manager = new TorrentManager(context);

            String magnet = item.getMagnetLink() != null ? item.getMagnetLink() : "";
            if (!magnet.isEmpty()) {
                manager.addMagnet(magnet, context.getCacheDir().getAbsolutePath());
            } else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getTorrentUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class TorrentViewHolder extends RecyclerView.ViewHolder {
        TextView title, size, quality, seeders;
        Button buttonDownload;

        public TorrentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTorrentTitle);
            size = itemView.findViewById(R.id.textTorrentSize);
            quality = itemView.findViewById(R.id.textTorrentQuality);
            seeders = itemView.findViewById(R.id.textTorrentSeeders);
            buttonDownload = itemView.findViewById(R.id.buttonDownloadTorrent);
        }
    }
}
