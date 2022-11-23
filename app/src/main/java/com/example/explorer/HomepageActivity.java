package com.example.explorer;

import static com.example.explorer.R.id;
import static com.example.explorer.R.layout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class HomepageActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    TextView textView36, textView37;
    int y, m, d;


    Float starRating;
    private ViewHomePagerAdapter viewHomePagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_homepage);
        toolbar = findViewById(R.id.topAppbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigationbar);
        textView36 = findViewById(id.textView36);
        textView37 = findViewById(id.textView37);
        tabLayout = findViewById(R.id.tab_layout);


        // below code if for showing the image on the home screen along with tab-bar


        viewPager = findViewById(R.id.viewpager);

        // setting up the adapter
        viewHomePagerAdapter = new ViewHomePagerAdapter(getSupportFragmentManager());


        // add the fragments
        viewHomePagerAdapter.add(new China(), "China");
        viewHomePagerAdapter.add(new France(), "France");
        viewHomePagerAdapter.add(new India(), "India");
        viewHomePagerAdapter.add(new Italy(), "Italy");
        viewHomePagerAdapter.add(new Japan(), "Japan");
        viewHomePagerAdapter.add(new Thailand(), "Thailand");
        viewHomePagerAdapter.add(new Usa(), "Usa");
        viewHomePagerAdapter.add(new Vietnam(), "Vietnam");
        viewPager.setAdapter(viewHomePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        // below code is for calendar

        final Calendar c = Calendar.getInstance();
        textView36.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HomepageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView36.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });

        // below code is for storing the details of the no of guest

        textView37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(v);

                //  textView37.setText(imgr.toString());
            }
        });

        // below code will give the prompt whether there is internet connection available or not

        if (!isNetworkAvailable() == true) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connection Alert")
                    .setMessage("Please Check Your Internet Connection")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        } else if (isNetworkAvailable() == true) {
            Toast.makeText(HomepageActivity.this,
                    "Welcome", Toast.LENGTH_LONG).show();
        }


        // tap target code below here ( or hints )

        new TapTargetSequence(this).targets(
                TapTarget.forView(bottomNavigationView.findViewById(R.id.home), "HOME", "Use for going on the home page")
                        .outerCircleColor(R.color.teal_200)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                TapTarget.forView(bottomNavigationView.findViewById(R.id.search), "Search",
                                "Use for searching the hotel around you")
                        .outerCircleColor(R.color.teal_200)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                TapTarget.forView(bottomNavigationView.findViewById(R.id.location), "LOCATION",
                                "Use for searching the best place to travel")
                        .outerCircleColor(R.color.teal_200)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                TapTarget.forView(bottomNavigationView.findViewById(R.id.userprofile), "USERPROFILE",
                                "Use for seeing and editing the user profile")
                        .outerCircleColor(R.color.teal_200)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                TapTarget.forView(navigationView.findViewById(R.id.navigation_view), "NAVIGATION DRAWER",
                                "Use for more exciting features")
                        .outerCircleColor(R.color.red)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(5)).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                Toast.makeText(HomepageActivity.this, "Sequence Finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                Toast.makeText(HomepageActivity.this, "GREAT!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {

            }
        }).start();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(HomepageActivity.this, "ALREADY ON HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        Toast.makeText(HomepageActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        Toast.makeText(HomepageActivity.this, "LOCATION", Toast.LENGTH_SHORT).show();
                        Intent newIntentmaps = new Intent(HomepageActivity.this, LocationpageActivity.class);
                        startActivity(newIntentmaps);
                        break;
                    case R.id.userprofile:
                        Toast.makeText(HomepageActivity.this, "USERPROFILE", Toast.LENGTH_SHORT).show();
                        Intent newIntent = new Intent(HomepageActivity.this, UserprofilepageActivity.class);
                        startActivity(newIntent);
                        break;
                    default:

                }
                return (true);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.nav_home:
                        Toast.makeText(HomepageActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_search:
                        Toast.makeText(HomepageActivity.this, "SEARCH", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_location:
                        Toast.makeText(HomepageActivity.this, "LOCATION", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_userprofile:
                        Toast.makeText(HomepageActivity.this, "USERPROFILE", Toast.LENGTH_SHORT).show();
                        Intent newIntent3 = new Intent(HomepageActivity.this, UserprofilepageActivity.class);
                        startActivity(newIntent3);
                        break;

                    case R.id.nav_rate:
                        Toast.makeText(HomepageActivity.this, "RATE OUR APP..", Toast.LENGTH_SHORT).show();
                        RatingDialog RatingDialog = new RatingDialog(HomepageActivity.this);
                        RatingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                        RatingDialog.setCancelable(false);
                        RatingDialog.show();
                        break;

                    case R.id.nav_share:
                        Toast.makeText(HomepageActivity.this, "INVITE YOUR FRIENDS", Toast.LENGTH_SHORT).show();
                        ShareDialog ShareDialog = new ShareDialog(HomepageActivity.this, R.style.ShareDialogStyle);
                        ShareDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                        ShareDialog.setCancelable(true);
                        ShareDialog.show();
                        break;

                    case R.id.nav_developer:
                        Toast.makeText(HomepageActivity.this, "DEVELOPED BY..", Toast.LENGTH_SHORT).show();
                        Intent newIntent4 = new Intent(HomepageActivity.this, DevelopersActivity.class);
                        startActivity(newIntent4);
                        finish();
                        break;

                    case R.id.nav_feedback:
                        FeedbackDialog feedbackDialog = new FeedbackDialog(HomepageActivity.this);
                        feedbackDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                        feedbackDialog.setCancelable(true);
                        feedbackDialog.show();
                        break;

                    case R.id.nav_settings:
                        Toast.makeText(HomepageActivity.this, "SETTINGS", Toast.LENGTH_SHORT).show();
                        break;


                    default:

                }
                return true;
            }

        });


    }

    private void setText(View v) {
        String newText = textView37.getText().toString();
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        textView37.setText(newText);
        textView37.setText("");

    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                        return true;
                    } else
                        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                }
            }
        }
        return false;

    }
}