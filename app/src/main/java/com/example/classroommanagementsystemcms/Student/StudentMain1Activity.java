package com.example.classroommanagementsystemcms.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.classroommanagementsystemcms.MainActivity;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.SplashScreen;
import com.example.classroommanagementsystemcms.Student.CR.CRFragment;
import com.example.classroommanagementsystemcms.Student.Dashboard.DashboardFragment;
import com.example.classroommanagementsystemcms.Student.Course.DownloadFragment;
import com.example.classroommanagementsystemcms.Student.Key.StudentKeyFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StudentMain1Activity extends AppCompatActivity {

    MeowBottomNavigation meo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        meo=(MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_vpn_key_24));





        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;

                switch (item.getId()){
                    case 1:
                        fragment=new StudentKeyFragment();
                        break;

                }

                loadFragment(fragment);

            }
        });
        meo.show(1,true);

        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }



    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }




}