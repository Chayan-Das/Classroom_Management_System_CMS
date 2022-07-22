package com.example.classroommanagementsystemcms.Staff.Last;

import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Maintanance.AddProjector;
import com.example.classroommanagementsystemcms.Student.Key.Roomlist;
import com.example.classroommanagementsystemcms.Student.Key.Roommodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class Recordlist extends FirebaseRecyclerAdapter<RecordModel, Recordlist.myviewholder> {

    public Recordlist(@NonNull FirebaseRecyclerOptions<RecordModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull RecordModel model) {

        holder.textView.setText(model.getStudentname());
        holder.textView1.setText("Date: "+model.getGetkeyid());
        holder.textView2.setText("Room no- "+model.getRoomno());
        holder.textView4.setText(model.getRoll());
        holder.textView5.setText("Phone no: "+model.getPhoneno());

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Key Purchase Record");
        ref.orderByChild("getkeyid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){

                    String returntime = ""+ds.child("returntime").getValue();

                    if (!returntime.isEmpty()){

                        holder.textView3.setVisibility(View.VISIBLE);
                        holder.textView3.setText(returntime);




                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recordlist_rec,parent,false);
        return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder {

        TextView textView,textView1,textView2,textView3,textView4,textView5;

        public myviewholder(View view) {
            super(view);

            textView=view.findViewById(R.id.s_name);
            textView1=view.findViewById(R.id.date);
            textView2=view.findViewById(R.id.room_name);
            textView3=view.findViewById(R.id.indicator);
            textView4=view.findViewById(R.id.roll);
            textView5=view.findViewById(R.id.phone);



        }
    }
}
