package com.example.myapplication;

import static android.app.ProgressDialog.show;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;



import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUp extends Fragment {


private TextInputEditText name,phone,email,password,conpassword,userName;
private FirebaseAuth mAuth;
private FirebaseFirestore db;
private Button btnSignUp;
    public SignUp() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        name=view.findViewById(R.id.name);
        userName=view.findViewById(R.id.etName);
        phone=view.findViewById(R.id.phone);
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        conpassword=view.findViewById(R.id.conpassword);
        btnSignUp=view.findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForm();


            }
        });


        return view;
    }
    private void checkForm(){
        if(!(email.getText().toString().isEmpty() || name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || password.getText().toString().isEmpty() || conpassword.getText().toString().isEmpty())) {


                if (name.getText().toString().length() >= 4 && checkname()) {

                    if (checkphone()) {

                    if (checkemail()) {


                        if (checkpassword()) {


                            if (password.getText().toString().equals(conpassword.getText().toString())) {
                                String mail=email.getText().toString();
                                String pass=password.getText().toString();
                                registerNewUser(mail,pass);

                                Toast.makeText(getActivity(), "Sign Up Successfull", Toast.LENGTH_SHORT).show();
                                addUserDataToFirebase();
                                MainActivity.homeFrame.setVisibility(View.INVISIBLE);
                                MainActivity.dashFrame.setVisibility(View.INVISIBLE);
                                MainActivity.loginFrame.setVisibility(View.VISIBLE);
                                MainActivity. signUpFrame.setVisibility(View.INVISIBLE);



                            } else
                                Toast.makeText(getActivity(), "Passwords not match", Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(getActivity(), "Password must be at least 6 characters and contain  letter,number,capital letter", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }else
                        Toast.makeText(getActivity(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();

            }
                else
                    Toast.makeText(getActivity(), "Name must be at least 5 characters and contain only letters", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
    }

    private void addUserDataToFirebase() {
        com.example.myapplication.User user=new User(name.getText().toString(),phone.getText().toString(),email.getText().toString(),userName.getText().toString());
        db.collection("users").document(user.getMail()).set(user);
        name.setText(null);
        phone.setText(null);
        email.setText(null);
        password.setText(null);
        conpassword.setText(null);

    }

    private boolean checkname(){
        char [] nameArray=name.getText().toString().toCharArray();
        boolean flag=true;
        for(int i=0;i<nameArray.length;i++){
            if(!Character.isLetter(nameArray[i]) )
                flag=false;}
        return flag;
}
    private boolean checkphone(){
        char [] phoneArray=phone.getText().toString().toCharArray();
        boolean flag=true;
        if(phone.getText().toString().length()!=10)
            return false;
        for(int i=0;i<phoneArray.length;i++){
            if(!Character.isDigit(phoneArray[i]) )
                flag=false;}
            return flag;

    }
    private boolean checkemail(){
      return Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
    }
    private boolean checkpassword(){
        boolean flag=false;
        char [] passwordArray=password.getText().toString().toCharArray();
        int countCapital=0;
        int countSmall=0;
        int countNumber=0;
        for(int i=0;i<passwordArray.length;i++){
            if(Character.isUpperCase(passwordArray[i]))
                countCapital++;
            else if(Character.isLowerCase(passwordArray[i]))
                countSmall++;
            else if(Character.isDigit(passwordArray[i]))
                countNumber++;

        }
        if(countCapital>=1 && countSmall>=1 && countNumber>=1)
            flag=true;

        if(password.getText().toString().length() <6)
            flag=false;

        return flag;


    }
    private void registerNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password);



    }
    }
