package com.animrift.app.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animrift.app.R;
import com.animrift.app.adapter.TorrentAdapter;
import com.animrift.app.api.NyaaAPI;
import com.animrift.app.torrent.TorrentModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText editSearch;
    private Button buttonSearch;
    private RecyclerView recyclerView;
    private TorrentAdapter adapter;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editSearch = view.findViewById(R.id.editSearch);
        buttonSearch = view.findViewById(R.id.buttonSearch);
        recyclerView = view.findViewById(R.id.recyclerTorrent);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new TorrentAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        buttonSearch.setOnClickListener(v -> {
            String query = editSearch.getText().toString().trim();
            if (TextUtils.isEmpty(query)) {
                Toast.makeText(requireContext(), "يرجى إدخال اسم الأنمي", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                try {
                    List<TorrentModel> results = NyaaAPI.search(query);
                    requireActivity().runOnUiThread(() -> {
                        if (results == null || results.isEmpty()) {
                            Toast.makeText(requireContext(), R.string.error_no_results, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        adapter.updateList(results);
                    });
                } catch (Exception e) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(requireContext(), "فشل البحث: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();
        });

        return view;
    }
}
