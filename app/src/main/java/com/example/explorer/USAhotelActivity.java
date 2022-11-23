package com.example.explorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class USAhotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usahotel);
        TextView textView47=findViewById(R.id.textView47);
        textView47.setText("Miami Beachside.. \nit is surrounded by greenery a" +
                " very good environment with pool" +
                " so guest can live their lives to the fullest...");
        TextView continuebookusa=findViewById(R.id.continuebookusa);
        continuebookusa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(USAhotelActivity.this, "Redirecting to payment gateway-->", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),PaymentconfirmActivity.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.offgreycolor3));
        }
    }
}