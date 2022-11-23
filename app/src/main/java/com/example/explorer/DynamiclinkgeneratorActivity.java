package com.example.explorer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class DynamiclinkgeneratorActivity extends AppCompatActivity {
    TextView createlink;
    ImageView cancelbutton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamiclinkgenerator);
        createlink = findViewById(R.id.CREATELINKBUTTON);
        cancelbutton2=findViewById(R.id.cancel_button2);

        createlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createlink();
            }
        });
        createlink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(DynamiclinkgeneratorActivity.this,"hello world",Toast.LENGTH_SHORT).show();
                return true;

            }
        });

        cancelbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        // for changing the color of status bar


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.creamcolor));
    }

    private void createlink() {
        Log.e("main", "create link");
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()

                // below link will be for github where application is uploaded


                .setLink(Uri.parse("https://www.example.com/"))
                .setDomainUriPrefix("explorertraveladvisor.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios")
                        .build())
                // Here we can the no of people clicked on the link
                .setGoogleAnalyticsParameters(
                        new DynamicLink.GoogleAnalyticsParameters.Builder()
                                .setSource("orkut")
                                .setMedium("social")
                                .setCampaign("example-promo")
                                .build())
                .buildDynamicLink();


        Uri dynamicLinkUri = dynamicLink.getUri();
        Log.e("main", "Long refer link" + dynamicLink.getUri());

        // manually generating link

        String sharelinktext = "https://explorertraveladvisor.page.link/?" +
                "https://github.com/" + // here github profile link will come where application is uploaded
                "&apn= " + getPackageName() +
                "&st " + "EXPLORER Refer link" +
                "%si " + "https://unsplash.com/images/travel"; // here "" image uri will come

        // below code is for shoring the link


        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                // .setLongLink(dynamicLink.getUri())
                .setLongLink(Uri.parse(sharelinktext))
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            Log.e("main", "short link " + shortLink);

                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);

                            assert shortLink != null;
                            intent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                            intent.setType("text/plain");
                            startActivity(intent);

                        } else {
                            Log.e("main", "error " + task.getException());
                        }
                    }
                });


    }
}