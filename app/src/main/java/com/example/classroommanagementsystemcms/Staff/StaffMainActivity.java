package com.example.classroommanagementsystemcms.Staff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Dashboard.StaffDashboardFragment;
import com.example.classroommanagementsystemcms.Staff.Maintanance.MaintananceFragment;

public class StaffMainActivity extends AppCompatActivity {

    MeowBottomNavigation meo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);

        meo=(MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_dashboard_24));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_report_alt_24));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_maintanance_services_24));





        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;

                switch (item.getId()){
                    case 1:
                        fragment=new StaffDashboardFragment();
                        break;
                    case 2:
                        fragment=new KeyReportFragment();
                        break;
                    case 3:
                        fragment=new MaintananceFragment();
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