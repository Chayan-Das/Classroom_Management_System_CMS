package com.example.classroommanagementsystemcms.Staff.Create;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Keyfragments.KeypurchasedetailsFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class Roomlist extends FirebaseRecyclerAdapter<Roommodel, Roomlist.myviewholder> {


    public Roomlist(@NonNull FirebaseRecyclerOptions<Roommodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Roomlist.myviewholder holder, int position, @NonNull Roommodel model) {


        holder.textView.setText(model.getRoomname());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new KeypurchasedetailsFragment()).addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public Roomlist.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_roomlist,parent,false);
        return new Roomlist.myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public myviewholder(View view) {
            super(view);

            textView=view.findViewById(R.id.roomno);
            imageView=view.findViewById(R.id.key_operation);
        }
    }
}
