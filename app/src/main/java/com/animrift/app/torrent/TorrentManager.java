package com.animrift.app.torrent;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import org.libtorrent4j.AlertListener;
import org.libtorrent4j.Session;
import org.libtorrent4j.TorrentHandle;
import org.libtorrent4j.alerts.Alert;
import org.libtorrent4j.alerts.MetadataReceivedAlert;
import org.libtorrent4j.alerts.TorrentAddedAlert;
import org.libtorrent4j.alerts.TorrentFinishedAlert;

import java.io.File;

public class TorrentManager {

    private final Context context;
    private final Session session;
    private final File cacheDir;

    public TorrentManager(@NonNull Context context) {
        this.context = context.getApplicationContext();
        this.session = new Session();
        this.cacheDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "AnimeRift/Downloads"
        );
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
    }

    public void addMagnet(@NonNull String magnetLink, @NonNull String saveDir) {
        try {
            File destination = new File(saveDir);
            if (!destination.exists()) destination.mkdirs();

            TorrentHandle handle = session.addTorrent(magnetLink, destination.getAbsolutePath());

            session.addListener(new AlertListener() {
                @Override
                public void alert(Alert<?> alert) {
                    if (alert instanceof TorrentAddedAlert) {
                    } else if (alert instanceof MetadataReceivedAlert) {
                    } else if (alert instanceof TorrentFinishedAlert) {
                    }
                }
            });

            if (handle != null) {
                handle.resume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTorrentFileAvailable(@NonNull String filePath) {
        File file = new File(filePath);
        return file.exists() && file.length() > 0;
    }

    public String getCacheDirectory() {
        return cacheDir.getAbsolutePath();
    }

    public void stopSession() {
        try {
            if (session != null) {
                session.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
