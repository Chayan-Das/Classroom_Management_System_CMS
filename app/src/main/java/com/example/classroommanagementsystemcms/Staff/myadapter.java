package com.example.classroommanagementsystemcms.Staff;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroommanagementsystemcms.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class myadapter extends FirebaseRecyclerAdapter<Batchmodel, myadapter.myviewholder> {



    public myadapter(@NonNull FirebaseRecyclerOptions<Batchmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myadapter.myviewholder holder, int position, @NonNull Batchmodel model) {

        holder.BATCHNAME.setText(model.getBatchname());

    }

    @NonNull
    @Override
    public myadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_batch,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView BATCHNAME;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            BATCHNAME=(TextView)itemView.findViewById(R.id.batch_title);
        }
    }

}
