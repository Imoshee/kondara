package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class loginFrag extends Fragment {

TextInputEditText textUser,textPassword;
Button login;

    public loginFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        textUser = view.findViewById(R.id.etName);
        textPassword=view.findViewById(R.id.password);
        login=view.findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textUser.getText().toString();
                String pass = textPassword.getText().toString();
                if(user.equals("kondara")&& pass.equals("1234")){
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
                return view;
    }
}