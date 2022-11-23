package com.example.explorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShareapkActivity extends AppCompatActivity {
    List<App> apps = new ArrayList<>();
    RecyclerView recyclerView;
    AppAdapter appAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareapk);

        recyclerView = findViewById(R.id.app_list);

        //Get App List
        PackageManager pm  = getApplicationContext().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for(ApplicationInfo packageInfo: packages){

            String name;

            if((name = String.valueOf(pm.getApplicationLabel(packageInfo))).isEmpty()){
                name = packageInfo.packageName;
            }

            Drawable icon  = pm.getApplicationIcon(packageInfo);
            String apkPath = packageInfo.sourceDir;
            long apkSize = new File(packageInfo.sourceDir).length();
            apps.add(new App(name,icon,apkPath,apkSize));
        }



        Collections.sort(apps, new Comparator<App>() {
                    @Override
                    public int compare(App app, App t1) {
                        return app.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
                    }
                }
        );
/*
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if ("android.intent.action.SEND".equals(action) && type != null && "text/plain".equals(type)) {
            Log.println(Log.ASSERT,"shareablTextExtra",intent.getStringExtra("android.intent.extra.TEXT"));
        }

 */


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        appAdapter = new AppAdapter(this,apps);
        recyclerView.setAdapter(appAdapter);



    }
}