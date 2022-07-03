package com.example.classroommanagementsystemcms.Staff.Maintanance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Student.Signup;
import com.example.classroommanagementsystemcms.Student.StudentMainActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BatchActivity extends AppCompatActivity {

    TextView count_batch,pending;
    FloatingActionButton create_batch_dialog;
    RecyclerView all_batches_recview;
    myadapter adapter;
    private ArrayList<Batchmodel> dataModalArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);

        create_batch_dialog=findViewById(R.id.create_batch_dialog);
        all_batches_recview=findViewById(R.id.all_batches_recview);
        count_batch=findViewById(R.id.count_batch);




        create_batch_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(BatchActivity.this);
                View mview=getLayoutInflater().inflate(R.layout.create_batch,null);

                final TextInputLayout create_new_batch=(TextInputLayout) mview.findViewById(R.id.create_new_batch);
                Button save_batch = (Button) mview.findViewById(R.id.save_year);
                ImageButton close_dialog = (ImageButton) mview.findViewById(R.id.close_dialog);
                RelativeLayout success_message = (RelativeLayout) mview.findViewById(R.id.success_message);
                RelativeLayout l1 = (RelativeLayout) mview.findViewById(R.id.l1);

                alert.setView(mview);

                final AlertDialog alertDialog=alert.create();
                alertDialog.setCanceledOnTouchOutside(true);


                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                save_batch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String creat_new_batch = create_new_batch.getEditText().getText().toString();

                        if(TextUtils.isEmpty(creat_new_batch)) {
                            create_new_batch.setError("Batch is empty");
                            return;
                        }

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("batch_name",""+creat_new_batch);
                        //hashMap.put("batch year","1st");
                        hashMap.put("varify Status","No");

                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Batch");
                        ref.child(creat_new_batch).setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {


                                        HashMap<String, Object> hashMap1 = new HashMap<>();
                                        hashMap1.put("education year","1st year");

                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Batch").child(creat_new_batch).child("Year");
                                        ref.child("1st year").setValue(hashMap1)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {



                                                        HashMap<String, Object> hashMap2 = new HashMap<>();
                                                        hashMap2.put("education year","2nd year");

                                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Batch").child(creat_new_batch).child("Year");
                                                        ref.child("2nd year").setValue(hashMap2)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {


                                                                        HashMap<String, Object> hashMap3 = new HashMap<>();
                                                                        hashMap3.put("education year","3rd year");

                                                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Batch").child(creat_new_batch).child("Year");
                                                                        ref.child("3rd year").setValue(hashMap3)
                                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                    @Override
                                                                                    public void onSuccess(Void unused) {



                                                                                        HashMap<String, Object> hashMap4 = new HashMap<>();
                                                                                        hashMap4.put("education year","4th year");


                                                                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Batch").child(creat_new_batch).child("Year");
                                                                                        ref.child("4th year").setValue(hashMap4)
                                                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onSuccess(Void unused) {

                                                                                                        l1.setVisibility(View.GONE);
                                                                                                        success_message.setVisibility(View.VISIBLE);

                                                                                                        finish();
                                                                                                        overridePendingTransition(0,3);
                                                                                                        startActivity(getIntent());
                                                                                                        overridePendingTransition(0,3);





                                                                                                    }
                                                                                                }).addOnFailureListener(new OnFailureListener() {
                                                                                            @Override
                                                                                            public void onFailure(@NonNull Exception e) {

                                                                                            }
                                                                                        });





                                                                                    }
                                                                                }).addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e) {

                                                                            }
                                                                        });

                                                                    }
                                                                }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                            }
                                                        });


                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });



                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    }
                });

                alertDialog.show();



            }
        });



        dataModalArrayList = new ArrayList<>();
        all_batches_recview.setHasFixedSize(true);

        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        all_batches_recview.setLayoutManager(layoutManager);

        adapter = new myadapter(dataModalArrayList,this);

        all_batches_recview.setAdapter(adapter);

        loadrec();



    }

    private void loadrec() {
        Query query=FirebaseDatabase.getInstance().getReference("Batch").orderByChild("varify Status").equalTo("Yes");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Batchmodel batchmodel= ds.getValue(Batchmodel.class);
                    dataModalArrayList.add(batchmodel);
                    adapter=new myadapter(dataModalArrayList,BatchActivity.this);
                    all_batches_recview.setAdapter(adapter);

                    int batchcount = (int) snapshot.getChildrenCount();
                    count_batch.setText(""+batchcount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }






}