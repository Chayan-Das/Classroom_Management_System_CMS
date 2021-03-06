package com.example.classroommanagementsystemcms.Student.Key;

import android.app.FragmentTransaction;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classroommanagementsystemcms.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;


public class ScheduleKeyFragment extends Fragment {

    TextView room_no;
    Button release_key;
    RelativeLayout purchase_status;


    public ScheduleKeyFragment() {
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

        View view = inflater.inflate(R.layout.fragment_schedule_key, container, false);
        mainFragment(view);

        return view;
    }

    private void mainFragment(View view) {

        room_no=view.findViewById(R.id.room_no);
        release_key=view.findViewById(R.id.release_key);
        purchase_status=view.findViewById(R.id.purchase_status);


        FirebaseAuth fAuth;
        fAuth= FirebaseAuth.getInstance();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Student_Account");
        ref.orderByChild("studentid").equalTo(fAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    String accessid = ""+ds.child("studentid").getValue();
                    String roomno = ""+ds.child("Purchase Room").getValue();


                    if(roomno.isEmpty()){

                        release_key.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "No key is occupied", Toast.LENGTH_LONG).show();
                            }
                        });

                    }

                    else {

                        room_no.setText(roomno);




                        release_key.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Student_Account");
                                ref.orderByChild("studentid").equalTo(fAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot ds: snapshot.getChildren()){

                                            String id = ""+ds.child("Purchase Room").getValue();


                                            HashMap<String, Object> hashMap3 = new HashMap<>();
                                            hashMap3.put("purchasedby","empty");

                                            DatabaseReference ref2= FirebaseDatabase.getInstance().getReference("Rooms").child(id);
                                            ref2.updateChildren(hashMap3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {



                                                }
                                            });


                                            HashMap<String, Object> hashMap2 = new HashMap<>();
                                            hashMap2.put("Purchase Room","");



                                            DatabaseReference ref3= FirebaseDatabase.getInstance().getReference("Student_Account").child(accessid);
                                            ref3.updateChildren(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {



                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });



                                            String pid = ""+ds.child("Purchase id").getValue();


                                            String time = new SimpleDateFormat("hh:mm aa" ).format(Calendar.getInstance().getTime());

                                            HashMap<String, Object> hashMap4 = new HashMap<>();
                                            hashMap4.put("returntime",""+time);


                                            DatabaseReference ref4= FirebaseDatabase.getInstance().getReference("Key Purchase Record").child(pid);
                                            ref4.updateChildren(hashMap4).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    Toast.makeText(getActivity(), "Key is now free", Toast.LENGTH_LONG).show();



                                                }
                                            });


                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });




                            }
                        });



                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }





}