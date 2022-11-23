package com.example.explorer;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushNotificationActivity extends AppCompatActivity {


    EditText editTextTextPersonName12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_push_notification);
        getSupportActionBar().hide();
       // editTextTextPersonName12= findViewById(R.id.editTextTextPersonName12);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast

                        System.out.println(token);
                        Toast.makeText(PushNotificationActivity.this, "Your Device Registration Token is " + token, Toast.LENGTH_SHORT).show();

                        //editTextTextPersonName12.setText(token);
                    }
                });
    }
}