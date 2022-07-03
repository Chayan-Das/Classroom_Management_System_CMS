package com.example.classroommanagementsystemcms.Student.Key;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.text.format.DateFormat;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Maintanance.BatchActivity;
import com.example.classroommanagementsystemcms.Staff.Maintanance.BatchDetailsStaff;
import com.example.classroommanagementsystemcms.Staff.Maintanance.Batchmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Roomlist extends FirebaseRecyclerAdapter<Roommodel, Roomlist.myviewholder> {


    public Roomlist(@NonNull FirebaseRecyclerOptions<Roommodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Roommodel model) {


        holder.textView.setText(model.getRoomname());

        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                View mview =LayoutInflater.from(v.getContext()).inflate(R.layout.getkey, (ViewGroup) v, false);

                final TextView room_no=(TextView) mview.findViewById(R.id.room_no);
                ImageButton close_dialog = (ImageButton) mview.findViewById(R.id.close_dialog);
                RelativeLayout success_message = (RelativeLayout) mview.findViewById(R.id.success_message);
                RelativeLayout l1 = (RelativeLayout) mview.findViewById(R.id.l1);
                Button coform_purchase=(Button) mview.findViewById(R.id.coform_purchase);

                alert.setView(mview);

                final AlertDialog alertDialog=alert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                //String room = room_no.getText().toString();
                //room_no.setText(model.getRoomname());

                coform_purchase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                        room_no.setText(date);


                    }
                });



                alertDialog.show();









            }
        });



    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_roomlist,parent,false);
        return new myviewholder(view);
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
