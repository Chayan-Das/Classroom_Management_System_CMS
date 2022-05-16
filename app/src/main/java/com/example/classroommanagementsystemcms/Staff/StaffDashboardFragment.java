package com.example.classroommanagementsystemcms.Staff;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.classroommanagementsystemcms.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StaffDashboardFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    Animation rotateforward, rotatebackward;
    MaterialCardView materialCardView;

    TextInputLayout mbatchname;
    Button mcreate;
    DatabaseReference databaseCreate;

    myadapter adapter;

    RecyclerView recyclerView;




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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff_dashboard, container, false);

        main(view);
        addFragment(view);


        return view;
    }

    private void main(View view) {

        floatingActionButton = view.findViewById(R.id.create_batch);
        materialCardView = view.findViewById(R.id.portion1);


        //anime
        rotateforward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_forward);
        rotatebackward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_backward);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animateFab();


            }
        });


    }

    private void animateFab(){

        if(materialCardView.getVisibility()==View.VISIBLE){

            materialCardView.setVisibility(View.GONE);
            floatingActionButton.setAnimation(rotatebackward);
        }
        else{
            materialCardView.setVisibility(View.VISIBLE);
            floatingActionButton.setAnimation(rotateforward);
        }


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