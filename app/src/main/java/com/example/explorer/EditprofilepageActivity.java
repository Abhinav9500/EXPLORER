package com.example.explorer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class EditprofilepageActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText editTextTextPersonName8,editTextTextPersonName9,editTextTextPersonName10,editTextTextPersonName11;
    Button button4;
    ImageView imageView18,imageView22;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    FirebaseUser user;
    StorageReference storageReference;
    Integer counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofilepage);
        Intent data=getIntent();
        String username= data.getStringExtra("username");
        String email= data.getStringExtra("email");
        String phoneno= data.getStringExtra("phoneno");
        String address= data.getStringExtra("address");

        firebaseAuth= FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference();


        editTextTextPersonName8=findViewById(R.id.editTextTextPersonName8);
        editTextTextPersonName9=findViewById(R.id.editTextTextPersonName9);
        editTextTextPersonName10=findViewById(R.id.editTextTextPersonName10);
        editTextTextPersonName11=findViewById(R.id.editTextTextPersonName11);
        button4=findViewById(R.id.button4);
        imageView18=findViewById(R.id.imageView18);
        imageView22=findViewById(R.id.imageView22);

        //StorageReference profileRef = storageReference.child("users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid() + "profile.jpg");
        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "profile.jpg");

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(imageView18);

            }
        });

        // here image can be captured using camera available on the user phone ( AFTER CLICK OF THE BUTTON )
        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditprofilepageActivity.this,"Camera Image Clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),OpencameraforprofileActivity.class));
            }
        });

        // this is the edit profile image code


        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // these are the two lines of code which is used for opening the gallery


                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
                Toast.makeText(EditprofilepageActivity.this, "Select Image from Gallery", Toast.LENGTH_SHORT).show();
            }
        });


        // save button code


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextTextPersonName8.getText().toString().isEmpty() ||  editTextTextPersonName9.getText().toString().isEmpty() || editTextTextPersonName10.getText().toString().isEmpty() || editTextTextPersonName11.getText().toString().isEmpty())
                {
                    Toast.makeText(EditprofilepageActivity.this,"One or many fields are empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                String email=editTextTextPersonName9.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference documentReference = firestore.collection("users").document(user.getUid());
                        Map<String,Object> edited=new HashMap<>();
                        edited.put("email",email);
                        edited.put("username",editTextTextPersonName8.getText().toString());
                        edited.put("phoneno",editTextTextPersonName10.getText().toString());
                        edited.put("address",editTextTextPersonName11.getText().toString());
                        documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                              Toast.makeText(EditprofilepageActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(getApplicationContext(),UserprofilepageActivity.class));
                              finish();
                            }
                        });
                        Toast.makeText(EditprofilepageActivity.this,"Email Is Changed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditprofilepageActivity.this, e.getMessage() ,Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        editTextTextPersonName8.setText(username);
        editTextTextPersonName9.setText(email);
        editTextTextPersonName10.setText(phoneno);
        editTextTextPersonName11.setText(address);

        Log.d(TAG,"onCreate" + username + " " + email + " " + phoneno + " " + address);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                uploadImageToFirebase(imageUri);
            }
        }
    }

    // this code will help in uploading the profile photo of the user on firebase storage


    private void uploadImageToFirebase(Uri imageUri) {

        //upload image to firebase storage

        final StorageReference fileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageView18);
                     //   Picasso.get().load(uri).into(imageView12);


                    }
                });

                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Image Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}