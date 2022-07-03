package com.example.classroommanagementsystemcms.Student;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.classroommanagementsystemcms.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    TextInputLayout Name,Id,Email,Password;
    ImageView imageView;
    Button Create;
    ImageButton backBtn;
    private FirebaseAuth fAuth;
    MaterialCardView progress_dialog;



    private static final int STORAGE_REQUEST_CODE = 100;
    private static final int IMAGE_PICK_GALLER_CODE = 200;


    private String[] storagepermissions;
    private Uri image_uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Name = findViewById(R.id.name);
        Id = findViewById(R.id.roll);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        imageView = findViewById(R.id.insetr_student_image);

        progress_dialog = findViewById(R.id.progress_dialog);

        Create = findViewById(R.id.create);
        backBtn=findViewById(R.id.backBtn);

        storagepermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        fAuth=FirebaseAuth.getInstance();



        //backbutton

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //----------------------------------

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkstoragepermissions()){
                    pickFromgallery();
                }
                else {
                    requeststoragepermission();
                }

            }
        });

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputData();

            }
        });



    }

    private String fullname;
    String email;
    String password;
    String roll;

    private void inputData() {
        progress_dialog.setVisibility(View.VISIBLE);

       fullname = Name.getEditText().getText().toString();
       roll = Id.getEditText().getText().toString();
       email = Email.getEditText().getText().toString();
       password = Password.getEditText().getText().toString();

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

        createAccount();

    }

    private void createAccount() {

        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(
                        new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                saveData();

                            }
                        }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void saveData() {

        if(image_uri==null){

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("studentid",""+fAuth.getUid());
            hashMap.put("Name",""+fullname);
            hashMap.put("Email",""+email);
            hashMap.put("Roll",""+roll);
            hashMap.put("Role","Student");
            hashMap.put("Batch","2k"+roll.substring(0,2));
            hashMap.put("Type","Regular");
            hashMap.put("ProfileImage","");

            //save

            DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Student_Account");
            ref.child(fAuth.getUid()).setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            startActivity(new Intent(Signup.this, StudentMainActivity.class));
                            finish();
                            progress_dialog.setVisibility(View.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }

        else
        {
            String Filepathandname = "profile_images"+""+fAuth.getUid();

            StorageReference storageReference= FirebaseStorage.getInstance().getReference(Filepathandname);
            storageReference.putFile(image_uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                            while (!uriTask.isSuccessful());
                            Uri downloadimageuri = uriTask.getResult();

                            if(uriTask.isSuccessful()){

                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("studentid",""+fAuth.getUid());
                                hashMap.put("Name",""+fullname);
                                hashMap.put("Email",""+email);
                                hashMap.put("Roll",""+roll);
                                hashMap.put("Role","Student");
                                hashMap.put("Batch","2k"+roll.substring(0,2));
                                hashMap.put("Type","Regular");
                                hashMap.put("ProfileImage",""+downloadimageuri);

                                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Student_Account");
                                ref.child(fAuth.getUid()).setValue(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                startActivity(new Intent(Signup.this, StudentMainActivity.class));
                                                finish();
                                                progress_dialog.setVisibility(View.GONE);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });




        }
    }


    private  void pickFromgallery(){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLER_CODE);

    }

    private void requeststoragepermission(){
        ActivityCompat.requestPermissions(this,storagepermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkstoragepermissions(){


        boolean result1= ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )==(PackageManager.PERMISSION_GRANTED);

        return result1;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)  {

        switch (requestCode){

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted= grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(storageAccepted ){
                        pickFromgallery();
                    }
                    else {
                        Toast.makeText(this,"Storage permissions necessary",Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode==RESULT_OK){

            if(requestCode==IMAGE_PICK_GALLER_CODE){
                image_uri=data.getData();
                imageView.setImageURI(image_uri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}