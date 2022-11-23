package com.example.explorer;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {
    Button button10;
    RatingBar ratingBar;
    String message;
    float myrating=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        button10=findViewById(R.id.button10);
        ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                int rating =(int) v;
                myrating=(int) ratingBar.getRating();
                String message=null;
                switch ((int) v){
                    case 1:
                        message="sorry to hera that :( ";
                        break;
                    case 2:
                        message="We always accept suggestions: ";
                        break;
                    case 3:
                        message="Good enough:";
                        break;
                    case 4:
                        message="Great! Thank Yor:";
                        break;
                    case 5:
                        message="Awesome! You are the best";
                        break;

                }
                Toast.makeText(RatingActivity.this,"Your rating is"+message, Toast.LENGTH_SHORT).show();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RatingActivity.this, String.valueOf(myrating), Toast.LENGTH_SHORT).show();
            }
        });
    }
}