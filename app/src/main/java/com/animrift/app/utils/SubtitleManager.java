package com.animrift.app.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SubtitleManager {

    public static File downloadSubtitle(Context context, String query, String language) {
        File dir = new File(context.getCacheDir(), "subtitles");
        if (!dir.exists()) dir.mkdirs();

        String sampleSubtitle = "1\n00:00:00,000 --> 00:00:02,000\nمرحبا بك في Anime Rift\n\n";
        File subtitleFile = new File(dir, "sample_arabic.srt");

        try (FileOutputStream fos = new FileOutputStream(subtitleFile)) {
            fos.write(sampleSubtitle.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return subtitleFile;
    }

    public static void attachSubtitleToPlayer(TextView textView, String subtitlePath) {
        File file = new File(subtitlePath);
        if (!file.exists()) return;

        try {
            textView.setTextColor(Color.WHITE);
            textView.setText("الترجمة العربية: " + file.getName());
        } catch (Exception e) {
            textView.setText("تعذر تحميل الترجمة");
        }
    }
}
