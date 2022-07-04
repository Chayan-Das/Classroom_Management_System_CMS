package com.example.classroommanagementsystemcms.Student.Key;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Student.Dashboard.DashboardFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class StudentKeyFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;


    public StudentKeyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_student_key, container, false);
        mainFragment(view);

        return view;
    }

    private void mainFragment(View view) {
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager_student_dashboard);

        DashboardFragment.MainAdapter adapter=new DashboardFragment.MainAdapter(getChildFragmentManager());

        adapter.AddFragment(new ScheduleKeyFragment(),"Purchased Key");
        adapter.AddFragment(new CustomKeyFragment(),"Custom Purchase");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    static class MainAdapter extends FragmentPagerAdapter {

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