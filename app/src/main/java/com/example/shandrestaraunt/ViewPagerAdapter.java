package com.example.shandrestaraunt;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


//this sets the pagge based on the fragment number
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MapFragment();
            case 2:
                return new ReserveFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Number of fragments
    }
}
