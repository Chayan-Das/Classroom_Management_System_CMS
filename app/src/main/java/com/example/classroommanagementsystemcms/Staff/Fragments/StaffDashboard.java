package com.example.classroommanagementsystemcms.Staff.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.classroommanagementsystemcms.R;

public class StaffDashboard extends AppCompatActivity {

    MeowBottomNavigation meo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        meo=(MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_vpn_key_24));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_vpn_key_24));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_edit_24));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_cloud_download_24));





        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {


            }
        });

        meo.show(1,true);

        replace(new StaffKeyFragment());



        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {


                switch (item.getId()){
                    case 1:
                        replace(new StaffKeyFragment());
                        break;
                    case 2:
                        replace(new StaffKeyFragment());
                        break;
                    case 3:
                        replace(new StaffCreateFragment());
                        break;
                    case 4:
                        replace(new StaffDownloadFragment());
                        break;
                }

            }
        });




        meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {


            }
        });


    }

    private void replace(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();

    }
}