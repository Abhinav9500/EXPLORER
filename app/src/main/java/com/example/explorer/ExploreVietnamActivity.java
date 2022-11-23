package com.example.explorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class ExploreVietnamActivity extends AppCompatActivity {
    ViewPagerVietnam viewpagervietnam;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView imageView31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_vietnam);
        ViewPager viewPager=findViewById(R.id.viewpagervietnam);
        viewpagervietnam = new ViewPagerVietnam(getSupportFragmentManager());
        viewpagervietnam.add(new ExplorevietnamhotelFragment(), "Hotels");
        viewPager.setAdapter(viewpagervietnam);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        imageView31=findViewById(R.id.imageView31);


        imageView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.lightgreyforthailand));
        }
    }
}