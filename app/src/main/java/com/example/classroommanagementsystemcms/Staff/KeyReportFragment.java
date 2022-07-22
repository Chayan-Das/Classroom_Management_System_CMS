package com.example.classroommanagementsystemcms.Staff;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Last.RecordModel;
import com.example.classroommanagementsystemcms.Staff.Last.Recordlist;
import com.example.classroommanagementsystemcms.Student.Key.Roomlist;
import com.example.classroommanagementsystemcms.Student.Key.Roommodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class KeyReportFragment extends Fragment {

    Recordlist adapter3;
    RecyclerView recyclerView;


    public KeyReportFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_key_report, container, false);
        mainFragment(view);

        return view;
    }

    private void mainFragment(View view) {

        recyclerView=view.findViewById(R.id.recordrec);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<RecordModel> options =
                new FirebaseRecyclerOptions.Builder<RecordModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Key Purchase Record"), RecordModel.class)
                        .build();

        adapter3=new Recordlist(options);
        recyclerView.setAdapter(adapter3);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter3.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter3.stopListening();
    }
}