package com.animrift.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.animrift.app.ui.DownloadsFragment;
import com.animrift.app.ui.HomeFragment;
import com.animrift.app.ui.SearchFragment;
import com.animrift.app.ui.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            showFragment(new HomeFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                showFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.nav_search) {
                showFragment(new SearchFragment());
                return true;
            } else if (itemId == R.id.nav_downloads) {
                showFragment(new DownloadsFragment());
                return true;
            } else if (itemId == R.id.nav_settings) {
                showFragment(new SettingsFragment());
                return true;
            }
            return false;
        });
    }

    private void showFragment(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
