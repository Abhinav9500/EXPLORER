package com.example.explorer;

import static java.util.Objects.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SignupActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button button2, button6;
    EditText editTextTextPersonName3, editTextTextPersonName7, editTextTextPersonName4, editTextTextPersonName5, editTextTextPersonName6;
    ProgressBar progressBar2;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
     //   requireNonNull(getSupportActionBar()).hide();

        button2 = findViewById(R.id.button2);
        button6 = findViewById(R.id.button6);
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressBar2 = findViewById(R.id.progressBar2);


        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomepageActivity.class));

            // here "HomepageActivity" is the name of the activity which comes next after completion of this task. yet to be made by yash
            // UserprofilepageActivity temporary used this at place of mainactivity

            finish();

        }


        button2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // all this code is for necessary details

                String signupusername = editTextTextPersonName3.getText().toString();
                String signupaddress = editTextTextPersonName6.getText().toString();
                String signupphoneno = editTextTextPersonName7.getText().toString();
                String signupemail = editTextTextPersonName4.getText().toString().trim();
                String signuppassword = editTextTextPersonName5.getText().toString().trim();
                if (TextUtils.isEmpty(signupusername)) {
                    editTextTextPersonName3.setError("Username cannot be empty");
                    return;
                }

                if (TextUtils.isEmpty(signupemail)) {
                    editTextTextPersonName4.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(signuppassword)) {
                    editTextTextPersonName4.setError("Password is required");
                    return;
                }

                if (signuppassword.length() < 7) {
                    editTextTextPersonName5.setError("Password must be 8 character long");
                    return;
                }

                if (signupphoneno.length() < 9 || signupphoneno.length() > 10) {
                    editTextTextPersonName7.setError("Phoneno must be 10 in length");
                    return;
                }
                progressBar2.setVisibility(View.VISIBLE);

                // register the user in firebase

                firebaseAuth.createUserWithEmailAndPassword(signupemail, signuppassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                            // startActivity(new Intent(getApplicationContext(), HomepageActivity.class));

                            // here "mainActivity" is the name of the activity which comes next after completion of this task

                            // from here we can store details of the user in the database

                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentRefrence = firestore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("username", signupusername);
                            user.put("email", signupemail);
                            user.put("phoneno", signupphoneno);
                            user.put("address", signupaddress);

                            // above this comment we can put any number of user.put code for multiple entries of personal data

                            documentRefrence.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for" + userID);

                                }
                            });

                        } else {
                            Toast.makeText(SignupActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);

                        }

                    }
                });
            }
        });

        // from here all the code is for " already have account "
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

                // here "LoginActivity is the name of the activity which has to proceed after the click of the button
            }
        });


    }
}