package com.example.classroommanagementsystemcms.Staff.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;


public class StaffDashboardFragment extends Fragment {

    

    public StaffDashboardFragment() {
        // Required empty public constructor
    }
    
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        View view= inflater.inflate(R.layout.fragment_staff_dashboard, container, false);
        main(view);

        return view;
    }

    private void main(View view) {
    }
}