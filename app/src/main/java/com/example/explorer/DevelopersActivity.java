package com.example.explorer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DevelopersActivity extends AppCompatActivity {
    TextView textView33,textView34,textView35,privacypolicy;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
//        textView32 = findViewById(R.id.textView32);
        textView33 = findViewById(R.id.textView33);
        textView34 = findViewById(R.id.textView34);
        textView35 = findViewById(R.id.textView35);
        privacypolicy = findViewById(R.id.privacypolicy);


        int unicode2 = 0x2665;
        int unicode1 = 0x2B50;
        int email = 0x2709;
        String emoji1 = getEmoji1(unicode1);
        String emoji2 = getEmoji2(unicode2);
        String emoji3  = getEmoji3(email);
        textView33.setText("If you liked our work show some " + emoji2 + " and " + emoji1 + " the repository");
        textView34.setText("Made with "+ emoji2 +" by " + "\n\n " +
                "Abhinav Jain\n"+emoji3+" abhinavjain95000@gmail.com\n\n" +
                "Divyansh Chourasia\n"+emoji3+ " divyanshchourasiadc490@gmail.com\n\n" +
                "Yash Rawat\n"+ emoji3+" yashrawat893@gmail.com\n\n" +
                "Vaibhav Jain\n"+emoji3+" vaibhavjain1400@gmail.com");

        textView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/therock/?hl=en");
            }
        });
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DevelopersActivity.this,PrivacypolicyActivity.class);
                startActivity(intent);

            }
        });

        // to change the color of status bar and it can also be used individually

        if (Build.VERSION.SDK_INT >= 23) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private String getEmoji3(int email) {
        return new String(Character.toChars(email));
    }

    private String getEmoji2(int unicode2) {
        return new String(Character.toChars(unicode2));
    }

    public String getEmoji1(int unicode1) {
        return new String(Character.toChars(unicode1));
    }
    }

