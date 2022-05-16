package com.example.classroommanagementsystemcms.Student;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter adapter;


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

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private class MainAdapter extends FragmentPagerAdapter {

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