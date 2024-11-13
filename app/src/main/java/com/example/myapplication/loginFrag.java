package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class loginFrag extends Fragment {

private TextInputEditText textUser,textPassword;
private Button btnlogin;
private FirebaseAuth mAuth;

    public loginFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        textUser = view.findViewById(R.id.etName);
        textPassword=view.findViewById(R.id.password);
        btnlogin=view.findViewById(R.id.button);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
checkEmailPassword();
                }

        });
                return view;
    }
    private void checkEmailPassword() {
        String email=textUser.getText().toString();
        String password=textPassword.getText().toString();
        if(!email.isEmpty() && !password.isEmpty()){
           mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       uptadeUi();

                       Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();

                   }
               }
           });
        }
        else{
            Toast.makeText(getActivity(), "Please enter email and password", Toast.LENGTH_SHORT).show();

        }


}

public void uptadeUi(){
        MainActivity.loginFrame.setVisibility(View.INVISIBLE);
        MainActivity.homeFrame.setVisibility(View.VISIBLE);
        MainActivity.dashFrame.setVisibility(View.INVISIBLE);
}
}