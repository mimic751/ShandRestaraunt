package com.example.shandrestaraunt;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {
//view pager is used to swipe between fragments
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Button homeButton;
    private Button mapButton;
    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//this part of the code is used to set the viewpager and the buttons
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        homeButton = findViewById(R.id.homeButton);
        mapButton = findViewById(R.id.mapButton);
        reserveButton = findViewById(R.id.reserveButton);

        // Set text color using ColorStateList
        homeButton.setTextColor(ContextCompat.getColorStateList(this, R.color.button_text_color));
        mapButton.setTextColor(ContextCompat.getColorStateList(this, R.color.button_text_color));
        reserveButton.setTextColor(ContextCompat.getColorStateList(this, R.color.button_text_color));
//this section makes the buttons do things when clicked
        homeButton.setOnClickListener(v -> viewPager.setCurrentItem(0));

        mapButton.setOnClickListener(v -> viewPager.setCurrentItem(1));

        reserveButton.setOnClickListener(v -> viewPager.setCurrentItem(2));
//this section is used to change the background of the buttons when they are clicked
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                updateButtonBackgrounds(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        // Initialize button backgrounds
        updateButtonBackgrounds(viewPager.getCurrentItem());
    }
//this changes the buttons backrounds depending on what is selected
    private void updateButtonBackgrounds(int position) {
        homeButton.setSelected(position == 0);
        mapButton.setSelected(position == 1);
        reserveButton.setSelected(position == 2);

        homeButton.setBackgroundResource(position == 0 ? R.drawable.button_background_selected : R.drawable.button_background);
        mapButton.setBackgroundResource(position == 1 ? R.drawable.button_background_selected : R.drawable.button_background);
        reserveButton.setBackgroundResource(position == 2 ? R.drawable.button_background_selected : R.drawable.button_background);
    }
}
