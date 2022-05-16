package com.example.classroommanagementsystemcms.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.classroommanagementsystemcms.Adapter.CourseListAdapter;
import com.example.classroommanagementsystemcms.HelperClass.TeacherCourseList;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Student.DashboardFragment;
import com.example.classroommanagementsystemcms.Student.DownloadFragment;
import com.example.classroommanagementsystemcms.Student.KeyFragment;
import com.example.classroommanagementsystemcms.Student.ProfileFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherDashboard extends AppCompatActivity {
    MeowBottomNavigation meo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        meo=(MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_dashboard_24));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_outline_person_24));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_vpn_key_24));
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
                        fragment=new ProfileFragment();
                        break;
                    case 3:
                        fragment=new KeyFragment();
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