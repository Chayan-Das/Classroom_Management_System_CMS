package com.example.classroommanagementsystemcms.account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.classroommanagementsystemcms.HelperClass.StudentHelperClass;
import com.example.classroommanagementsystemcms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Signup extends AppCompatActivity {

    TextInputLayout Name,Id,Email,Password;
    ImageView imageView;
    Button Create;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth fAuth;
    FirebaseUser user;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    private static final int IMAGE_PICK_GALLER_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;

    private String[] camerapermissions;
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

        Create = findViewById(R.id.create);

        fAuth= FirebaseAuth.getInstance();

        reference=FirebaseDatabase.getInstance().getReference("Student Accounts");

        //permissions

        camerapermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showimagepick();
            }
        });

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

    ////////////////////////////////////////////

    private void showimagepick() {

        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Pick Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(which==0){
                            //camera
                            if(checkstoragepermissions()){
                                pickFromcamera();
                            }
                            else{
                                requecamerapermission();
                            }


                        }
                        else {
                            //gallery
                            if(checkstoragepermissions()){
                                pickFromgallery();
                            }
                            else{
                                requeststoragepermission();
                            }

                        }


                    }
                })
                .show();

    }

    private  void pickFromgallery(){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLER_CODE);

    }

    private  void pickFromcamera(){
        ContentValues contentValues= new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_image_title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp_image_description");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);

    }

    private void requeststoragepermission(){
        ActivityCompat.requestPermissions(this,storagepermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkstoragepermissions(){
        boolean result= ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA
                )==(PackageManager.PERMISSION_GRANTED);

        boolean result1= ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )==(PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requecamerapermission(){
        ActivityCompat.requestPermissions(this,camerapermissions,CAMERA_REQUEST_CODE);
    }

    //////////////////////////////////////////////////////


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

        if(image_uri==null){

            String fullname = Name.getEditText().getText().toString();
            String roll = Id.getEditText().getText().toString();
            String email = Email.getEditText().getText().toString();
            String password = Password.getEditText().getText().toString();
            String role = "student";
            String batch = "2k"+roll.substring(0,2);
            String type = "Regular";
            String profileimage="";


            if (!TextUtils.isEmpty(fullname)){

                String id = reference.push().getKey();
                StudentHelperClass superAdminHelperClass= new StudentHelperClass(fullname,email,password,roll,role,batch,type,profileimage);

                reference.child(roll).setValue(superAdminHelperClass);

            }
            else {
                Toast.makeText(this, "Enter Name First", Toast.LENGTH_LONG).show();
            }

        }

        else {

            String profileimage = "profile_images/"+fAuth.getUid();

            StorageReference storageReference= FirebaseStorage.getInstance().getReference(profileimage);
            storageReference.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (uriTask.isSuccessful());

                    Uri downloadimageuri= uriTask.getResult();
                    if(uriTask.isSuccessful()){
                        String fullname = Name.getEditText().getText().toString();
                        String roll = Id.getEditText().getText().toString();
                        String email = Email.getEditText().getText().toString();
                        String password = Password.getEditText().getText().toString();
                        String role = "student";
                        String batch = "2k"+roll.substring(0,2);
                        String type = "Regular";
                        String profileimage=""+downloadimageuri;


                        if (!TextUtils.isEmpty(fullname)){

                            String id = reference.push().getKey();
                            StudentHelperClass superAdminHelperClass= new StudentHelperClass(fullname,email,password,roll,role,batch,type,profileimage);

                            reference.child(roll).setValue(superAdminHelperClass);

                        }

                    }



                }
            });

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted= grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted= grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickFromcamera();
                    }
                    else {
                        Toast.makeText(this,"Camera permissions necessary",Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted= grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(storageAccepted ){
                        pickFromcamera();
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
                assert data != null;
                image_uri=data.getData();
                imageView.setImageURI(image_uri);
            }

            else if(requestCode==IMAGE_PICK_CAMERA_CODE){
                assert data != null;
                image_uri=data.getData();
                imageView.setImageURI(image_uri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}