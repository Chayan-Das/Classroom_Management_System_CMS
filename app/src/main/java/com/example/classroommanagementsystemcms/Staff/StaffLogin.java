package com.example.classroommanagementsystemcms.Staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.classroommanagementsystemcms.MainActivity;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Student.StudentMain1Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StaffLogin extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextInputLayout mEmail, mPassword;
    MaterialButton loginButton;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser user;
    String email, password;
    public static final String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);


        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);


        fAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null) {
                    Intent intent = new Intent(StaffLogin.this, StaffMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }


            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling EditText is empty or no method.
                userSign();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //removeAuthSateListner is used  in onStart function just for checking purposes,it helps in logging you out.
        fAuth.removeAuthStateListener(mAuthListner);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListner != null) {
            fAuth.removeAuthStateListener(mAuthListner);
        }

    }

    private void userSign() {
        email = mEmail.getEditText().getText().toString();
        password = mPassword.getEditText().getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(StaffLogin.this, "Enter the correct Email", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(StaffLogin.this, "Enter the correct password", Toast.LENGTH_SHORT).show();
            return;
        }


        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {


                    Toast.makeText(StaffLogin.this, "Login error ", Toast.LENGTH_SHORT).show();

                } else {


                    checkIfEmailExist();




                }
            }
        });
    }

    private void checkIfEmailExist() {

        final String userEnteredUsername = mEmail.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student_Account");
        Query query = reference.orderByChild("Email").equalTo(userEnteredUsername);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!snapshot.exists()) {

                    mEmail.getEditText().getText().clear();

                    mPassword.getEditText().getText().clear();

                    Intent intent = new Intent(StaffLogin.this, StaffMainActivity.class);

                    // Sending Email to Dashboard Activity using intent.
                    intent.putExtra(userEmail, email);

                    startActivity(intent);



                }
                else {

                    Toast.makeText(StaffLogin.this, "Account not found ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}