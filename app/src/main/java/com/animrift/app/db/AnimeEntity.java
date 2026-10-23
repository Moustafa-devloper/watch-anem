package com.animrift.app.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "anime_library")
public class AnimeEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private String imageUrl;
    private String episodeNumber;
    private String status;
    private String lastWatched;
    private boolean favorite;
    private String videoUrl;

    public AnimeEntity() {
    }

    public AnimeEntity(long id, String title, String imageUrl, String episodeNumber,
                       String status, String lastWatched, boolean favorite, String videoUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.episodeNumber = episodeNumber;
        this.status = status;
        this.lastWatched = lastWatched;
        this.favorite = favorite;
        this.videoUrl = videoUrl;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(String episodeNumber) { this.episodeNumber = episodeNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLastWatched() { return lastWatched; }
    public void setLastWatched(String lastWatched) { this.lastWatched = lastWatched; }

    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
}
