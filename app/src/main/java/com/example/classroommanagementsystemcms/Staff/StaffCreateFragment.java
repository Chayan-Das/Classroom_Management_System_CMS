package com.example.classroommanagementsystemcms.Staff;

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

    myadapter adapter;

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

        return view;
    }

    private void addFragment(View view) {


        databaseCreate = FirebaseDatabase.getInstance().getReference("Batch");

        recyclerView=view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<Batchmodel> options =
                new FirebaseRecyclerOptions.Builder<Batchmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Batch"), Batchmodel.class)
                        .build();

        adapter=new myadapter(options);
        recyclerView.setAdapter(adapter);

        mbatchname=view.findViewById(R.id.add_batch);
        mcreate=view.findViewById(R.id.save_batch);

        mcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addbatch();

            }
        });






    }







    private void addbatch() {

        String batchname = mbatchname.getEditText().getText().toString();


        if (!TextUtils.isEmpty(batchname)){

            String id = databaseCreate.push().getKey();
            Batchmodel batchmodel= new Batchmodel(id,batchname);

            databaseCreate.child(id).setValue(batchmodel);

        }


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