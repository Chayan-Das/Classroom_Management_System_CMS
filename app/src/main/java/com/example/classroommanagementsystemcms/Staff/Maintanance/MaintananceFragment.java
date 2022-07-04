package com.example.classroommanagementsystemcms.Staff.Maintanance;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;
import com.google.android.material.card.MaterialCardView;


public class MaintananceFragment extends Fragment {

    MaterialCardView acdemicyearcard,batchcard,roomcard,syllebuscard,teachercard;


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

        acdemicyearcard=view.findViewById(R.id.academicyearcard);
        batchcard=view.findViewById(R.id.batchcard);
        roomcard=view.findViewById(R.id.roomcard);
        syllebuscard=view.findViewById(R.id.syllebuscard);
        teachercard=view.findViewById(R.id.teacher_card);



        acdemicyearcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), com.example.classroommanagementsystemcms.Staff.Maintanance.AcademicYearMain.class));
            }
        });

        batchcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), com.example.classroommanagementsystemcms.Staff.Maintanance.BatchActivity.class));

            }
        });

        roomcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), com.example.classroommanagementsystemcms.Staff.Maintanance.Addkey.class));

            }
        });

    }

}