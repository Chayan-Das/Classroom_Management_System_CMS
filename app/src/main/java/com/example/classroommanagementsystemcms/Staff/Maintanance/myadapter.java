package com.example.classroommanagementsystemcms.Staff.Maintanance;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroommanagementsystemcms.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {

private ArrayList<Batchmodel> dataModalArrayList;
private Context context;


    public myadapter(ArrayList<Batchmodel> dataModalArrayList, Context context) {
        this.dataModalArrayList = dataModalArrayList;
        this.context = context;
    }




    @NonNull
    @Override
    public myadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myadapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_batch, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.ViewHolder holder, int position) {

        final Batchmodel batchmodel = dataModalArrayList.get(position);
        holder.batch_name.setText(batchmodel.getBatch_name());
        holder.batch_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BatchDetailsStaff.class);

                intent.putExtra("id",batchmodel.getBatch_name());

                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModalArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView batch_name;
        private final MaterialCardView batch_card;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            batch_name = itemView.findViewById(R.id.batch_title);
            batch_card = itemView.findViewById(R.id.batch_card);


        }
    }
}
