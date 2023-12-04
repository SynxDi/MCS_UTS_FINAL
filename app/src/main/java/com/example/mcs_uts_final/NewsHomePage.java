package com.example.mcs_uts_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NewsHomePage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    NewsFragment newsFragment = new NewsFragment();
    TicketFragment ticketFragment = new TicketFragment();

    Schedule schedule = new Schedule();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_home_page);
        bottomNavigationView = findViewById(R.id.bottomnav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
                        return true;
                    case R.id.Transaction:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,ticketFragment).commit();
                        return true;
                    case R.id.Schedule:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,schedule).commit();
                        return true;
                }
                return false;
            }
        });
    }
}