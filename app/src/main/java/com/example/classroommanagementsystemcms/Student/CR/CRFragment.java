package com.example.classroommanagementsystemcms.Student.CR;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.classroommanagementsystemcms.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.annotations.NonNull;


public class CRFragment extends Fragment {

    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;





    public CRFragment() {
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


        View view= inflater.inflate(R.layout.fragment_c_r, container, false);
        main(view);

        return view;
    }

    private void main(View view) {

        String[] type = new String[] {"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(
                getActivity(),
                R.layout.spinner_background,type
        );

        autoCompleteTextView=view.findViewById(R.id.day);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView1=view.findViewById(R.id.teacher_name);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Teachers");

        reference.addListenerForSingleValueEvent(valueEventListener);

        list= new ArrayList<>();
        adapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        autoCompleteTextView1.setAdapter(adapter);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list.clear();
            for(DataSnapshot mydata : snapshot.getChildren()){
                String projectorname= mydata.child("Name").getValue().toString();
                list.add(projectorname);



            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}