package com.animrift.app.api;

import com.animrift.app.torrent.TorrentModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class NyaaAPI {

    public static List<TorrentModel> search(String query) throws Exception {
        String url = "https://nyaa.si/?page=rss&q=" + URLEncoder.encode(query, "UTF-8");
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(15000)
                .get();

        Elements items = document.select("item");
        List<TorrentModel> results = new ArrayList<>();

        for (Element item : items) {
            String title = item.selectFirst("title") != null ? item.selectFirst("title").text() : "";
            String link = item.selectFirst("link") != null ? item.selectFirst("link").text() : "";
            String torrentLink = item.selectFirst("enclosure") != null ? item.selectFirst("enclosure").attr("url") : "";
            String size = item.selectFirst("nyaa:size") != null ? item.selectFirst("nyaa:size").text() : "غير معروف";
            String seeders = item.selectFirst("nyaa:seeders") != null ? item.selectFirst("nyaa:seeders").text() : "0";
            String quality = detectQuality(title);

            if (!title.isEmpty()) {
                TorrentModel model = new TorrentModel();
                model.setTitle(title);
                model.setSize(size);
                model.setQuality(quality);
                model.setSeeders(seeders);
                model.setTorrentUrl(torrentLink);
                model.setMagnetLink(link);
                results.add(model);
            }
        }

        return results;
    }

    private static String detectQuality(String title) {
        String lower = title.toLowerCase();
        if (lower.contains("1080p")) return "1080p";
        if (lower.contains("720p")) return "720p";
        if (lower.contains("480p")) return "480p";
        if (lower.contains("2160p")) return "2160p";
        return "SD";
    }
}
