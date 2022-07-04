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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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


                room_no.setText(model.getRoomname());


                coform_purchase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        FirebaseAuth fAuth;
                        fAuth= FirebaseAuth.getInstance();

                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Student_Account");
                        ref.orderByChild("studentid").equalTo(fAuth.getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds: snapshot.getChildren()){

                                    String name = ""+ds.child("Name").getValue();
                                    String roll = ""+ds.child("Roll").getValue();
                                    String phone = ""+ds.child("Phone Number").getValue();




                                    String date = new SimpleDateFormat("dd/MM/yyyy" ).format(Calendar.getInstance().getTime());

                                    String time = new SimpleDateFormat("hh:mm aa" ).format(Calendar.getInstance().getTime());


                                    String purchase_id = ""+System.currentTimeMillis();

                                    HashMap<String, Object> hashMap1 = new HashMap<>();
                                    hashMap1.put("purchaseid",""+purchase_id);
                                    hashMap1.put("Student Name",""+name);
                                    hashMap1.put("Roll",""+roll);
                                    hashMap1.put("Phone no",""+phone);
                                    hashMap1.put("Date",""+date);
                                    hashMap1.put("Purchase Time",""+time);

                                    hashMap1.put("Room no",""+model.getRoomname());

                                    DatabaseReference ref8= FirebaseDatabase.getInstance().getReference("Key Purchase Record");
                                    ref8.child(purchase_id+roll).setValue(hashMap1)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    Otheroperation();

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

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



                alertDialog.show();









            }

            private void Otheroperation() {
                FirebaseAuth fAuth;
                fAuth= FirebaseAuth.getInstance();

                DatabaseReference refe= FirebaseDatabase.getInstance().getReference("Student_Account");
                        refe.orderByChild("studentid").equalTo(fAuth.getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds: snapshot.getChildren()){

                                    String name = ""+ds.child("Name").getValue();
                                    String roll = ""+ds.child("Roll").getValue();
                                    String phone = ""+ds.child("Phone Number").getValue();

                                    HashMap<String, Object> hashMap2 = new HashMap<>();
                                    hashMap2.put("Purchase Room",""+model.getRoomname());

                                    String Sid = ""+ds.child("studentid").getValue();


                                    DatabaseReference ref2= FirebaseDatabase.getInstance().getReference("Student_Account").child(Sid);
                                    ref2.updateChildren(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {



                                        }
                                    });



                                    HashMap<String, Object> hashMap3 = new HashMap<>();
                                    hashMap3.put("purchasedby",""+name);

                                    String rid = model.getId();


                                    DatabaseReference ref3= FirebaseDatabase.getInstance().getReference("Rooms").child(rid);
                                    ref3.updateChildren(hashMap3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {





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
