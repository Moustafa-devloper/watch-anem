package com.animrift.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.animrift.app.R;
import com.animrift.app.utils.SubtitleManager;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    private LibVLC libVLC;
    private MediaPlayer mediaPlayer;
    private android.view.SurfaceView surfaceView;
    private TextView textSubtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        surfaceView = findViewById(R.id.surfaceView);
        textSubtitle = findViewById(R.id.txtSubtitle);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("video_url");
        String subtitlePath = intent.getStringExtra("subtitle_path");

        if (videoUrl == null || videoUrl.isEmpty()) {
            textSubtitle.setText("لا توجد بيانات فيديو.");
            return;
        }

        try {
            ArrayList<String> options = new ArrayList<>();
            options.add("--aout=android_audiotrack");
            options.add("--audio-time-stretch");
            options.add("-vvv");

            libVLC = new LibVLC(this, options);
            mediaPlayer = new MediaPlayer(libVLC);

            mediaPlayer.attachViews(surfaceView, null, false, false);
            Media media = new Media(libVLC, Uri.parse(videoUrl));
            mediaPlayer.setMedia(media);
            mediaPlayer.play();

            if (subtitlePath != null && !subtitlePath.isEmpty()) {
                SubtitleManager.attachSubtitleToPlayer(textSubtitle, subtitlePath);
            }

            textSubtitle.setText("جاري التشغيل...");
        } catch (Exception e) {
            textSubtitle.setText("فشل في التشغيل: " + e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (libVLC != null) {
            libVLC.release();
        }
    }
}
