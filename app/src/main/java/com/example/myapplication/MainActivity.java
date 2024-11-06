package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
private DashFrag dashFrag;
private HomeFrag homeFrag;
private loginFrag loginFrag;
private FrameLayout homeFrame,dashFrame,loginFrame;

private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        homeFrame = findViewById(R.id.home_frame);
        dashFrame = findViewById(R.id.dash_frame);
        loginFrame = findViewById(R.id.login_frame);
        bottomNav = findViewById(R.id.bottom_navigation);
        begin();




    }
    private void begin(){
        homeFrag=new HomeFrag();
        dashFrag=new DashFrag();
        loginFrag=new loginFrag();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,homeFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.dash_frame,dashFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame,loginFrag).commit();
        dashFrame.setVisibility(View.INVISIBLE);
        loginFrame.setVisibility(View.INVISIBLE);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_home){
                    homeFrame.setVisibility(View.VISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);


                }

                if(item.getItemId()==R.id.menu_home){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.VISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);


                }

                if(item.getItemId()==R.id.menu_home){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.VISIBLE);


                }
                return true;
            }
        });

    }
}