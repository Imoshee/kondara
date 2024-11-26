package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
private DashFrag dashFrag;
private SignUp signUpFrag ;
private HomeFrag homeFrag;
public static boolean isLogin=false;
private loginFrag loginFrag;
public static FrameLayout homeFrame,dashFrame,loginFrame,signUpFrame;

private BottomNavigationView bottomNav;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loginFrame = findViewById(R.id.login_frame);
        signUpFrame=findViewById(R.id.signUp_frame);
        homeFrame = findViewById(R.id.home_frame);
        dashFrame = findViewById(R.id.dash_frame);
        bottomNav = findViewById(R.id.bottom_navigation);
        begin();




    }
    private void begin(){
        loginFrag=new loginFrag();
        signUpFrag=new SignUp();
        homeFrag=new HomeFrag();
        dashFrag=new DashFrag();

        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame,loginFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.signUp_frame,signUpFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,homeFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.dash_frame,dashFrag).commit();

        dashFrame.setVisibility(View.INVISIBLE);
        homeFrame.setVisibility(View.INVISIBLE);
        signUpFrame.setVisibility(View.INVISIBLE);
        loginFrame.setVisibility(View.VISIBLE);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.menu_login && !isLogin){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.VISIBLE);
                    signUpFrame.setVisibility(View.INVISIBLE);


                }

                if(item.getItemId()== R.id.menu_dashboard && isLogin){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.VISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                    signUpFrame.setVisibility(View.INVISIBLE);


                }

                if(item.getItemId()== R.id.menu_home && isLogin){
                    homeFrame.setVisibility(View.VISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                    signUpFrame.setVisibility(View.INVISIBLE);


                }




                return true;
            }
        });

    }
}