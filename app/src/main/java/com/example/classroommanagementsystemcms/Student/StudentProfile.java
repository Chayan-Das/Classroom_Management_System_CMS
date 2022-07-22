package com.example.classroommanagementsystemcms.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.classroommanagementsystemcms.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;

public class StudentProfile extends AppCompatActivity {

    TextView my_year;
    ImageView pro_image;
    TextView my_name,my_id,my_batch,my_email;
    ImageButton backBtn;
    MaterialCardView batchcard;
    Button update_info;
    RelativeLayout cr_pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        my_year = findViewById(R.id.my_year);
        my_name = findViewById(R.id.my_name);
        my_id = findViewById(R.id.my_id);
        pro_image = findViewById(R.id.pro_image);
        my_batch = findViewById(R.id.my_batch);
        my_email = findViewById(R.id.my_email);
        backBtn = findViewById(R.id.backBtn);




        FirebaseAuth fAuth;
        fAuth= FirebaseAuth.getInstance();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Student_Account");
        ref.orderByChild("studentid").equalTo(fAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    String name = ""+ds.child("Name").getValue();
                    String roll = ""+ds.child("Roll").getValue();
                    //String year = ""+ds.child("Phone Number").getValue();
                    String email = ""+ds.child("Email").getValue();
                    String batch = ""+ds.child("Batch").getValue();
                    String image = ""+ds.child("ProfileImage").getValue();

                    my_name.setText(name);
                    my_id.setText(roll);
                    my_email.setText(email);
                    my_batch.setText(batch);

                    if(!image.isEmpty()){

                        try {
                            Glide.with(StudentProfile.this).load(image).into(pro_image);
                        } catch (Exception e) {

                        }
                    }

                    else {

                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}