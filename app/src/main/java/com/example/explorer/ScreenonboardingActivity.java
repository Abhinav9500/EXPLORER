package com.example.explorer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ScreenonboardingActivity extends AppCompatActivity {

    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;
    SharedPreferences sharedPreferences;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenonboarding);
        backbtn = findViewById(R.id.button12);
        nextbtn = findViewById(R.id.button13);
        skipbtn = findViewById(R.id.button11);


        // below code is for opening this activity for one time only when it is first installed
        sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = sharedPreferences.getString("FirstTimeInstall", "");
        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(ScreenonboardingActivity.this, FingerprintlockActivity.class);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0) {

                    mSLideViewPager.setCurrentItem(getitem(-1), true);

                }

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 3)
                    mSLideViewPager.setCurrentItem(getitem(1), true);
                else {

                    Intent i = new Intent(ScreenonboardingActivity.this, FingerprintlockActivity.class);
                    // FingerprintlockActivity is the activity to which after execution we reach

                    startActivity(i);
                    finish();

                }

            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {


                skipbtn.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        @SuppressLint("ClickableViewAccessibility") Vibrator vb = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                        vb.vibrate(100);
                        return false;
                    }
                });

                Intent i = new Intent(ScreenonboardingActivity.this, FingerprintlockActivity.class);
                // FingerprintlockActivity is the activity to which after execution we reach
                startActivity(i);
                finish();


            }
        });

        mSLideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);
        mSLideViewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position) {

        dots = new TextView[2];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0) {

                backbtn.setVisibility(View.VISIBLE);

            } else {

                backbtn.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {

        return mSLideViewPager.getCurrentItem() + i;
    }


}