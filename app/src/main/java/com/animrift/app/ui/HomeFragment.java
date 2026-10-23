package com.animrift.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animrift.app.R;
import com.animrift.app.adapter.AnimeAdapter;
import com.animrift.app.db.AnimeDao;
import com.animrift.app.db.AnimeEntity;
import com.animrift.app.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private AppDatabase db;
    private AnimeDao animeDao;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        db = AppDatabase.getInstance(requireContext());
        animeDao = db.animeDao();

        List<AnimeEntity> animeList = new ArrayList<>();
        animeList.add(new AnimeEntity(1L, "Demon Slayer", "https://images.unsplash.com/photo-1578301978693-85fa9c0320b9", "الحلقة 18", "مكتمل", "2026-10-23", true, "https://example.com/video1.mp4"));
        animeList.add(new AnimeEntity(2L, "Attack on Titan", "https://images.unsplash.com/photo-1532012197267-da84d127e765", "الحلقة 9", "مستمر", "2026-10-23", false, "https://example.com/video2.mp4"));
        animeList.add(new AnimeEntity(3L, "Jujutsu Kaisen", "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c", "الحلقة 24", "مستمر", "2026-10-23", true, "https://example.com/video3.mp4"));
        animeList.add(new AnimeEntity(4L, "Solo Leveling", "https://images.unsplash.com/photo-1511512578047-dfb367046420", "الحلقة 12", "جديد", "2026-10-23", false, "https://example.com/video4.mp4"));

        adapter = new AnimeAdapter(animeList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
