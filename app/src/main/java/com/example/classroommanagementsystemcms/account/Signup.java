package com.example.classroommanagementsystemcms.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.classroommanagementsystemcms.HelperClass.StudentHelperClass;
import com.example.classroommanagementsystemcms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    TextInputLayout Name,Id,Email,Password;
    Button Create;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Name = findViewById(R.id.name);
        Id = findViewById(R.id.roll);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        Create = findViewById(R.id.create);

        fAuth= FirebaseAuth.getInstance();

        reference=FirebaseDatabase.getInstance().getReference("Student Accounts");


        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String fullname = Name.getEditText().getText().toString();
                String roll = Id.getEditText().getText().toString();
                String email = Email.getEditText().getText().toString();
                String password = Password.getEditText().getText().toString();


                if(TextUtils.isEmpty(email)) {
                    Email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(fullname)) {
                    Name.setError("Full Name is required");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Password.setError("Password is empty");
                    return;
                }
                if(TextUtils.isEmpty(roll)) {
                    Id.setError("Student Id is empty");
                    return;
                }




                signinuser(fullname,email,password);
            }
        });


    }

    private void signinuser(String fullname, String email, String password) {

        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    sendemail();
                    if(!user.isEmailVerified()){
                        Intent intent = new Intent(com.example.classroommanagementsystemcms.account.Signup.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

                }else{

                    String error = task.getException().toString();
                    Log.d("login",error);
                    Toast.makeText(com.example.classroommanagementsystemcms.account.Signup.this,"error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void sendemail() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(com.example.classroommanagementsystemcms.account.Signup.this,"check email for verification",Toast.LENGTH_LONG).show();
                        addinfo();

                    }
                }
            });
        }
    }


    private void addinfo() {
        String fullname = Name.getEditText().getText().toString();
        String roll = Id.getEditText().getText().toString();
        String email = Email.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();
        String role = "student";
        String batch = "2k"+roll.substring(0,2);
        String type = "Regular";


        if (!TextUtils.isEmpty(fullname)){

            String id = reference.push().getKey();
            StudentHelperClass superAdminHelperClass= new StudentHelperClass(fullname,email,password,roll,role,batch,type);

            reference.child(roll).setValue(superAdminHelperClass);

        }
        else {
            Toast.makeText(this, "Enter Name First", Toast.LENGTH_LONG).show();
        }

    }
}