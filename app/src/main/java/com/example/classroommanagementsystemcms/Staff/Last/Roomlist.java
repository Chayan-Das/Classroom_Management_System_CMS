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
import com.example.classroommanagementsystemcms.Student.Key.Roommodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

public class Roomlist extends FirebaseRecyclerAdapter<Roommodel, com.example.classroommanagementsystemcms.Staff.Last.Roomlist.myviewholder> {


    public Roomlist(@NonNull FirebaseRecyclerOptions<Roommodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Roomlist.myviewholder holder, int position, @NonNull Roommodel model) {


        holder.textView.setText(model.getRoomname());


    }




    @NonNull
    @Override
    public com.example.classroommanagementsystemcms.Staff.Last.Roomlist.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_roomlist,parent,false);
        return new com.example.classroommanagementsystemcms.Staff.Last.Roomlist.myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        RelativeLayout next;

        public myviewholder(View view) {
            super(view);

            textView=view.findViewById(R.id.roomno);
            imageView=view.findViewById(R.id.key_operation);
            next=view.findViewById(R.id.next);
        }
    }

}

