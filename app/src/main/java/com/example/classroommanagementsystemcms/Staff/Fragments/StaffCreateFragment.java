package com.example.classroommanagementsystemcms.Staff.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.Adapter.Teacherlist;
import com.example.classroommanagementsystemcms.HelperClass.AddTeacherHelperClass;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.classroommanagementsystemcms.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StaffCreateFragment extends Fragment {



    TextInputLayout mbatchname;
    Button mcreate;
    DatabaseReference databaseCreate;

    Teacherlist adapter;

    RecyclerView recyclerView;




    public StaffCreateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_staff_create, container, false);

        addFragment(view);
        main(view);

        return view;
    }

    private void main(View view) {
        recyclerView=view.findViewById(R.id.teacher_list);

        FirebaseRecyclerOptions<AddTeacherHelperClass> options =
                new FirebaseRecyclerOptions.Builder<AddTeacherHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Batch"), AddTeacherHelperClass.class)
                        .build();

        adapter=new Teacherlist(options);
        recyclerView.setAdapter(adapter);


    }

    private void addFragment(View view) {






    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }








}