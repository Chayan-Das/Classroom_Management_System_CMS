package com.example.classroommanagementsystemcms.Staff.Keyfragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Addkey;
import com.example.classroommanagementsystemcms.Staff.Create.Roomlist;
import com.example.classroommanagementsystemcms.Staff.Create.Roommodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class KeypurchasedetailsFragment extends Fragment {

    Roomlist adapter2;
    DatabaseReference firebaseDatabase;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;


    public KeypurchasedetailsFragment() {
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
        View view= inflater.inflate(R.layout.fragment_home_key, container, false);
        main(view);
        return view;
    }

    private void main(View view) {

        floatingActionButton = view.findViewById(R.id.create_room);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Addkey.class);
                startActivity(intent);
            }
        });

        recyclerView=view.findViewById(R.id.available_rooms_list);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions<Roommodel> options =
                new FirebaseRecyclerOptions.Builder<Roommodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Rooms"), Roommodel.class)
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