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

public class ExploreChinaActivity extends AppCompatActivity {
    ViewPagerChina viewpagerchina;
    TabLayout tabLayout;
    ImageView imageView31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_china);
        ViewPager viewPager=findViewById(R.id.viewpagerchina);
        viewpagerchina = new ViewPagerChina(getSupportFragmentManager());
        viewpagerchina.add(new ExplorechinahotelFragment(), "Hotels");
        viewpagerchina.add(new ExplorechinafoodFragment(),"Food");
        viewPager.setAdapter(viewpagerchina);
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
            window.setStatusBarColor(this.getResources().getColor(R.color.yellowforchina));
        }
    }
}