package com.example.classroommanagementsystemcms.Student.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.classroommanagementsystemcms.MainActivity;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.SplashScreen;
import com.example.classroommanagementsystemcms.Student.Dashboard.ClassesFragment;
import com.example.classroommanagementsystemcms.Student.Dashboard.ExamFragment;
import com.example.classroommanagementsystemcms.Student.StudentProfile;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView pro_image;



    public DashboardFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        addFragment(view);

        return view;


    }

    private void addFragment(View view) {


        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager_student_dashboard);

        MainAdapter adapter=new MainAdapter(getChildFragmentManager());

        adapter.AddFragment(new ClassesFragment(),"Class");
        adapter.AddFragment(new ExamFragment(),"Exam");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        pro_image=view.findViewById(R.id.pro_image);

        FirebaseAuth fAuth;
        fAuth= FirebaseAuth.getInstance();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Student_Account");
        ref.orderByChild("studentid").equalTo(fAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){


                    String image = ""+ds.child("ProfileImage").getValue();



                    try {
                        Glide.with(getActivity()).load(image).into(pro_image);
                    } catch (Exception e) {

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        pro_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), StudentProfile.class));
            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static class MainAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public MainAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        //constractor
        public void AddFragment(Fragment fragment, String s){

            fragmentArrayList.add(fragment);

            stringArrayList.add(s);

        }



        @NonNull
        @Override
        public Fragment getItem(int position) {

            return  fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }
}