package com.example.classroommanagementsystemcms.Student.Key;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Maintanance.Addkey;
import com.example.classroommanagementsystemcms.Student.Dashboard.DashboardFragment;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CustomKeyFragment extends Fragment {

    Roomlist adapter2;
    RecyclerView recyclerView;



    public CustomKeyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_custom_key, container, false);
        mainFragment(view);

        return view;
    }

    private void mainFragment(View view) {

        recyclerView=view.findViewById(R.id.available_rooms_list);


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));


        FirebaseRecyclerOptions<Roommodel> options =
                new FirebaseRecyclerOptions.Builder<Roommodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Rooms").orderByChild("status").equalTo("empty"), Roommodel.class)
                        .build();

        adapter2=new Roomlist(options);
        recyclerView.setAdapter(adapter2);










    }

    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }


}