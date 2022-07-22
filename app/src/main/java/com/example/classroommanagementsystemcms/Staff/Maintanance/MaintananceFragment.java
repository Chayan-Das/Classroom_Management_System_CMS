package com.example.classroommanagementsystemcms.Staff.Maintanance;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Last.Roomlist;
import com.example.classroommanagementsystemcms.Student.Key.Roommodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.FirebaseDatabase;


public class MaintananceFragment extends Fragment {

    MaterialCardView acdemicyearcard;

    com.example.classroommanagementsystemcms.Staff.Last.Roomlist adapter2;
    RecyclerView recyclerView;


    public MaintananceFragment() {
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
        View view= inflater.inflate(R.layout.fragment_maintanance, container, false);
        main(view);

        return view;
    }

    private void main(View view) {


        recyclerView=view.findViewById(R.id.available_rooms_list);


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));


        FirebaseRecyclerOptions<Roommodel> options =
                new FirebaseRecyclerOptions.Builder<Roommodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Rooms"), Roommodel.class)
                        .build();

        adapter2=new Roomlist(options);
        recyclerView.setAdapter(adapter2);

        acdemicyearcard=view.findViewById(R.id.academicyearcard);



        acdemicyearcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), Addkey.class));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }

}