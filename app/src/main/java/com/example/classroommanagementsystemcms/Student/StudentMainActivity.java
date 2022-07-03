package com.example.classroommanagementsystemcms.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Student.CR.CrFragment;
import com.example.classroommanagementsystemcms.Student.Dashboard.DashboardFragment;
import com.example.classroommanagementsystemcms.Student.Course.DownloadFragment;
import com.example.classroommanagementsystemcms.Student.Key.StudentKeyFragment;

public class StudentMainActivity extends AppCompatActivity {

    MeowBottomNavigation meo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        meo=(MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_dashboard_24));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_vpn_key_24));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_cr_preferences_24));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_cloud_download_24));





        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;

                switch (item.getId()){
                    case 1:
                        fragment=new DashboardFragment();
                        break;
                    case 2:
                        fragment=new StudentKeyFragment();
                        break;
                    case 3:
                        fragment=new CrFragment();
                        break;

                    case 4:
                        fragment=new DownloadFragment();
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