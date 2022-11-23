package com.example.explorer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

class RatingDialog extends Dialog {
   private float userRate = 0;

   public RatingDialog(@NonNull Context context) {
      super(context);
   }
   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.rating_dialog_layout);
      final AppCompatButton RateNowButton = findViewById(R.id.RateNowButton);
      final AppCompatButton LaterButton= findViewById(R.id.LaterButton);
      final RatingBar RatingBar = findViewById(R.id.RatingBar);
      final ImageView RatingImage= findViewById(R.id.RatingImages);

      RateNowButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });

      LaterButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });

      RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
         @Override
         public void onRatingChanged(android.widget.RatingBar ratingBar, float rating, boolean fromUser) {

            if (rating <= 1){
               RatingImage.setImageResource(R.drawable.onestar);

            }else if (rating <=2){
               RatingImage.setImageResource(R.drawable.twostar);
            }else if(rating <=3){
               RatingImage.setImageResource(R.drawable.threestar);
            }else if (rating <=4){
               RatingImage.setImageResource(R.drawable.fourstar);
            }else if(rating<=5){
               RatingImage.setImageResource(R.drawable.fivestar);
            }

            // animate all emoji

            animatedImage(RatingImage);

            userRate= rating;
         }
      });
   }

   private void animatedImage(ImageView RatingImage){
      ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f
              , Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
      scaleAnimation.setFillAfter(true);
      scaleAnimation.setDuration(200);
      RatingImage.startAnimation(scaleAnimation);

   }



}
