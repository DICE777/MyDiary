package com.example.mydiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener{

    public static final String TAG = MainActivity.class.getCanonicalName();

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    BottomNavigationView bottomNavigationView;

    Location currentLocation;

    int locationCount = 0; // if you check your location, then it will cancel. so we need the location count that you checked.
    String currentWeather;
    String currentAddress;
    String currentDateString;
    Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "Clicked first tab", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "Clicked second tab", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        return true;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "Clicked third tab", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                        return true;
                }
                return false;
            }
        });
    }



    public void onRequest(String command) {
        if (command != null) {
            if (command.equals("getCurrentLocation")) {
                getCurrentLocation();
            }
        }
    }

    public void getCurrentLocation() {
        currentDate = new Date();
        currentDateString = AppConstants.dateFormat3.format(currentDate);
        if (fragment2 != null) {
            fragment2.setDateString(currentDateString);
        }

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (currentLocation != null) {
                double latitude = currentLocation.getLatitude();
                double longitude = currentLocation.getLongitude();
                String message = "Last Location -> Latitude : " + latitude + "\nLongitude: " + longitude;
                println(message);
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigationView.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigationView.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigationView.setSelectedItemId(R.id.tab3);
        }
    }

    private void println(String data) {
        Log.d(TAG, data);
    }
}
