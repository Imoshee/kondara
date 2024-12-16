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
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUp extends Fragment {


private TextInputEditText name,phone,email,password,conpassword,userName;
private FirebaseAuth mAuth;
private FirebaseFirestore db;
private Button btnSignUp,btnClear;

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
        btnClear=view.findViewById(R.id.clear);
        password=view.findViewById(R.id.password);
        conpassword=view.findViewById(R.id.conpassword);
        btnSignUp=view.findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setText(null);
                name.setText(null);
                phone.setText(null);
                email.setText(null);
                password.setText(null);
                conpassword.setText(null);


            }
                                    });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForm();
            }
        });
        return view;
    }

    //check if all fields are filled and valid and add user to firebase
    private void checkForm(){
        if(checkFields()) {


                if ( checkname()) {

                    if (checkphone()) {

                    if (checkemail()) {


                        if (checkpassword()) {


                            if (password.getText().toString().equals(conpassword.getText().toString())) {
                                registerNewUser(email.getText().toString(),password.getText().toString());
                                Toast.makeText(getActivity(), "Sign Up Successfull", Toast.LENGTH_SHORT).show();
                                addUserDataToFirebase();
                                MainActivity.homeFrame.setVisibility(View.INVISIBLE);
                                MainActivity.dashFrame.setVisibility(View.INVISIBLE);
                                MainActivity.loginFrame.setVisibility(View.VISIBLE);
                                MainActivity. signUpFrame.setVisibility(View.INVISIBLE);



                            } else
                                conpassword.setError("Password does not match");

                        } else
                           password.setError("Password must be at least 6 characters and contain at least one capital letter, one small letter and one number");

                    } else
                        email.setError("Invalid Email");
                }else
                        phone.setError("Invalid Phone Number");

            }
                else
                    name.setError("Name must be at least 3 characters and contain only letters");

        }
        else
            Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
    }

    //add user data to firebase
    private void addUserDataToFirebase() {
        com.example.myapplication.User user=new User(name.getText().toString(),phone.getText().toString(),email.getText().toString(),userName.getText().toString());
        db.collection("users").document(user.getMail()).set(user);
        name.setText(null);
        phone.setText(null);
        email.setText(null);
        password.setText(null);
        conpassword.setText(null);
        userName.setText(null);

    }

//check if name is valid(at least 3 characters and contain only letters)
    private boolean checkname(){
        char [] nameArray=name.getText().toString().toCharArray();
        boolean flag=true;
       if (name.getText().toString().length() <3)
           return false;
        for(int i=0;i<nameArray.length;i++){
            if(!Character.isLetter(nameArray[i]) )
                flag=false;}
        return flag;
}

    //check if phone number is valid(10 digits)
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

    //check if email is valid
    private boolean checkemail(){
      return Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
    }

    //check if password is valid(at least 6 characters and contain at least one capital letter, one small letter and one number)
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

    //check if all fields are filled(not empty)
    public boolean checkFields(){
        return !(email.getText().toString().isEmpty() || name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || password.getText().toString().isEmpty() || conpassword.getText().toString().isEmpty());
    }

    //add user to firebase
    private void registerNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(),task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(getActivity(), "Sign Up failed", Toast.LENGTH_SHORT).show();

            }
        });


    }
    }
