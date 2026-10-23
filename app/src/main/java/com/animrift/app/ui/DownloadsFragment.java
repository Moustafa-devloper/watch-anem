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

import java.util.List;

public class DownloadsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_downloads, container, false);

        recyclerView = view.findViewById(R.id.recyclerDownloads);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        db = AppDatabase.getInstance(requireContext());
        AnimeDao dao = db.animeDao();
        List<AnimeEntity> downloads = dao.getAll();
        adapter = new AnimeAdapter(downloads);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
