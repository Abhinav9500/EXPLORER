package com.example.explorer;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class UserprofilepageActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button button7, button8, button9;
    TextView textView15, textView17, textView19, textView21, textView3, textView12;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userId;
    ImageView imageView12;
    StorageReference storageReference;
    FirebaseUser user;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofilepage);
        textView3 = findViewById(R.id.textView3);
        textView12 = findViewById(R.id.textView12);
        textView15 = findViewById(R.id.textView15);
        textView17 = findViewById(R.id.textView17);
        textView19 = findViewById(R.id.textView19);
        textView21 = findViewById(R.id.textView21);
        imageView12 = findViewById(R.id.imageView12);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        firebaseAuth = getInstance();
        user = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        // StorageReference profileRef = storageReference.child("users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid() + "profile.jpg");
        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView12);
            }

        });

        userId = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("users").document(userId);
        final ListenerRegistration listenerRegistration = documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                textView17.setText(documentSnapshot.getString("phoneno"));
                textView15.setText(documentSnapshot.getString("username"));
                textView19.setText(documentSnapshot.getString("email"));
                textView21.setText(documentSnapshot.getString("address"));
                textView3.setText(documentSnapshot.getString("username"));
                textView12.setText(documentSnapshot.getString("email"));

            }
        });


        // reset password button in userprofile code


        button8.setOnClickListener(v -> {
            EditText resetPassword = new EditText(v.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter your password atleast 8 character long");
            passwordResetDialog.setView(resetPassword);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {


                String newPassword = resetPassword.getText().toString();
                user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserprofilepageActivity.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserprofilepageActivity.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();

                    }
                });
            });
            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                // close the dialog

            });
            passwordResetDialog.create().show();
        });

        // logout button code


        button7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        // from here code will run for editing the profile


        button9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), EditprofilepageActivity.class);
                i.putExtra("username", textView15.getText().toString());
                i.putExtra("email", textView19.getText().toString());
                i.putExtra("phoneno", textView17.getText().toString());
                i.putExtra("address", textView21.getText().toString());
                startActivity(i);
            }
        });

        // to change the color of status bar and it can also be used individually

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.cyancolor));

        }


    }
}

