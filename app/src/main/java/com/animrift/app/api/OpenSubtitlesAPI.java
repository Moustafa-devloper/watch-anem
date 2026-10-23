package com.animrift.app.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class OpenSubtitlesAPI {

    private static final String BASE_URL = "https://api.opensubtitles.com/api/";
    private static final String API_KEY = "YOUR_OPENSUBTITLES_API_KEY";

    public interface Service {
        @Headers({
                "Api-Key: " + API_KEY,
                "User-Agent: AnimeRift/1.0"
        })
        @POST("v1/subtitles")
        Call<SubtitleResponse> search(@Body SubtitleRequest request);
    }

    public static Service create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public static class SubtitleRequest {
        @SerializedName("query")
        public String query;

        @SerializedName("languages")
        public String languages = "ara";

        public SubtitleRequest(String query) {
            this.query = query;
        }
    }

    public static class SubtitleResponse {
        @SerializedName("data")
        public List<SubtitleData> data;
    }

    public static class SubtitleData {
        @SerializedName("attributes")
        public SubtitleAttributes attributes;
    }

    public static class SubtitleAttributes {
        @SerializedName("download_count")
        public int downloadCount;
        @SerializedName("language")
        public String language;
        @SerializedName("release_name")
        public String releaseName;
        @SerializedName("files")
        public List<FileData> files;
    }

    public static class FileData {
        @SerializedName("file_id")
        public long fileId;
    }
}
