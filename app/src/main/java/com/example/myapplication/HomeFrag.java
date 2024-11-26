package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFrag extends Fragment {

private FloatingActionButton btnLogut;
    public HomeFrag() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        btnLogut=view.findViewById(R.id.btnLogout);
        btnLogut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });

        return view;
    }
    private void LogOut(){
        FirebaseAuth.getInstance().signOut();
        MainActivity.isLogin=false;
        MainActivity.loginFrame.setVisibility(View.VISIBLE);
        MainActivity.homeFrame.setVisibility(View.INVISIBLE);
        MainActivity.dashFrame.setVisibility(View.INVISIBLE);

    }
}