package com.example.classroommanagementsystemcms.Staff.Maintanance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Student.Dashboard.ClassesFragment;
import com.example.classroommanagementsystemcms.Student.Dashboard.DashboardFragment;
import com.example.classroommanagementsystemcms.Student.Dashboard.ExamFragment;
import com.example.classroommanagementsystemcms.Student.Signup;
import com.example.classroommanagementsystemcms.Student.StudentMainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AcademicYearMain extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ImageButton show_create_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_year_main);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager_staff);
        show_create_year=findViewById(R.id.show_create_year);


        viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);

        show_create_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(AcademicYearMain.this);
                View mview=getLayoutInflater().inflate(R.layout.custom_dialog_create_batch,null);

                final TextInputLayout create_new_year=(TextInputLayout) mview.findViewById(R.id.create_new_year);
                Button save_year = (Button) mview.findViewById(R.id.save_year);
                ImageButton close_dialog = (ImageButton) mview.findViewById(R.id.close_dialog);
                RelativeLayout success_message = (RelativeLayout) mview.findViewById(R.id.success_message);
                RelativeLayout l1 = (RelativeLayout) mview.findViewById(R.id.l1);



                alert.setView(mview);

                final AlertDialog alertDialog=alert.create();
                alertDialog.setCanceledOnTouchOutside(true);


                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                save_year.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String creat_new_year = create_new_year.getEditText().getText().toString();

                        if(TextUtils.isEmpty(creat_new_year)) {
                            create_new_year.setError("Year is empty");
                            return;
                        }

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("academic year id",""+creat_new_year);




                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Year");
                        ref.child(creat_new_year).setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {


                                        HashMap<String, Object> hashMap1 = new HashMap<>();
                                        hashMap1.put("education year","1st year");

                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Year").child(creat_new_year);
                                        ref.child("1st year").setValue(hashMap1)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {



                                                        HashMap<String, Object> hashMap2 = new HashMap<>();
                                                        hashMap2.put("education year","2nd year");

                                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Year").child(creat_new_year);
                                                        ref.child("2nd year").setValue(hashMap2)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {


                                                                        HashMap<String, Object> hashMap3 = new HashMap<>();
                                                                        hashMap3.put("education year","3rd year");

                                                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Year").child(creat_new_year);
                                                                        ref.child("3rd year").setValue(hashMap3)
                                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                    @Override
                                                                                    public void onSuccess(Void unused) {



                                                                                        HashMap<String, Object> hashMap4 = new HashMap<>();
                                                                                        hashMap4.put("education year","4th year");


                                                                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Year").child(creat_new_year);
                                                                                        ref.child("4th year").setValue(hashMap4)
                                                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onSuccess(Void unused) {

                                                                                                        l1.setVisibility(View.GONE);
                                                                                                        success_message.setVisibility(View.VISIBLE);




                                                                                                    }
                                                                                                }).addOnFailureListener(new OnFailureListener() {
                                                                                            @Override
                                                                                            public void onFailure(@NonNull Exception e) {

                                                                                            }
                                                                                        });





                                                                                    }
                                                                                }).addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e) {

                                                                            }
                                                                        });

                                                                    }
                                                                }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                            }
                                                        });


                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });




                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    }
                });

                alertDialog.show();




            }
        });





        
    }




}