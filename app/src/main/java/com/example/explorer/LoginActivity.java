package com.example.explorer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    Button button, button3;
    EditText editTextTextPersonName, editTextTextPersonName2;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    TextView textView22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // getActionBar().hide();
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        button = findViewById(R.id.button);
        button3 = findViewById(R.id.button3);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        textView22 = findViewById(R.id.textView22);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomepageActivity.class));

            // here "MainActivity" is the name of the activity which comes next after complition of this task. yet to be made by yash
            // UserprofilepageActivity temporary used this at place of mainactivity

            finish();

        }


        button.setOnClickListener(v -> {

            // all this code is for necessary details

            String loginemail = editTextTextPersonName.getText().toString().trim();
            String loginpassword = editTextTextPersonName2.getText().toString().trim();
            if (TextUtils.isEmpty(loginemail)) {
                editTextTextPersonName.setError("Email is required");
                return;
            }
            if (TextUtils.isEmpty(loginpassword)) {
                editTextTextPersonName2.setError("Password is required");
                return;
            }
            if (loginpassword.length() < 7) {
                editTextTextPersonName2.setError("Password must be 8 character long");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);

            // register the user in firebase

            firebaseAuth.signInWithEmailAndPassword(loginemail, loginpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                        Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                        // here "HomepageActivity" is the name of the activity which comes next after completion of this task

                    } else
                    {
                        Toast.makeText(LoginActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        textView22.setOnClickListener(v -> {
            EditText resetMail = new EditText(v.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter your email to receive reset link");
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
              // extract the email and send reset link
              String mail = resetMail.getText().toString();
              firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                    Toast.makeText(LoginActivity.this,"Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(LoginActivity.this,"Error! Reset Link Is Not Sent"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
            });
            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                // close the dialog

            });
            passwordResetDialog.create().show();
        });
    }

}


