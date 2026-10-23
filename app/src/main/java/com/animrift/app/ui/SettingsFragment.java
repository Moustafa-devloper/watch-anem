package com.animrift.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.animrift.app.R;
import com.animrift.app.utils.PrefsManager;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {

    private SwitchMaterial switchDarkMode;
    private SwitchMaterial switchAutoSubtitle;
    private SwitchMaterial switchNotifications;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        switchDarkMode = view.findViewById(R.id.switchDarkMode);
        switchAutoSubtitle = view.findViewById(R.id.switchAutoSubtitle);
        switchNotifications = view.findViewById(R.id.switchNotifications);

        switchDarkMode.setChecked(PrefsManager.getBoolean(requireContext(), "dark_mode", true));
        switchAutoSubtitle.setChecked(PrefsManager.getBoolean(requireContext(), "auto_subtitle", true));
        switchNotifications.setChecked(PrefsManager.getBoolean(requireContext(), "download_notifications", true));

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) ->
                PrefsManager.putBoolean(requireContext(), "dark_mode", isChecked));

        switchAutoSubtitle.setOnCheckedChangeListener((buttonView, isChecked) ->
                PrefsManager.putBoolean(requireContext(), "auto_subtitle", isChecked));

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                PrefsManager.putBoolean(requireContext(), "download_notifications", isChecked));

        return view;
    }
}
