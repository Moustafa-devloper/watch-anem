package com.animrift.app.torrent;

public class TorrentModel {
    private String title;
    private String size;
    private String quality;
    private String seeders;
    private String magnetLink;
    private String torrentUrl;
    private String downloadUrl;

    public TorrentModel() {
    }

    public TorrentModel(String title, String size, String quality, String seeders, String magnetLink) {
        this.title = title;
        this.size = size;
        this.quality = quality;
        this.seeders = seeders;
        this.magnetLink = magnetLink;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }

    public String getSeeders() { return seeders; }
    public void setSeeders(String seeders) { this.seeders = seeders; }

    public String getMagnetLink() { return magnetLink; }
    public void setMagnetLink(String magnetLink) { this.magnetLink = magnetLink; }

    public String getTorrentUrl() { return torrentUrl; }
    public void setTorrentUrl(String torrentUrl) { this.torrentUrl = torrentUrl; }

    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }
}
