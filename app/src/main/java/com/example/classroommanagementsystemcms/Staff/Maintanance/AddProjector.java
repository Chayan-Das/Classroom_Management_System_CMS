package com.example.classroommanagementsystemcms.Staff.Maintanance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.classroommanagementsystemcms.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddProjector extends AppCompatActivity {

    FloatingActionButton madd_projector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_projector);

        madd_projector=findViewById(R.id.add_projector);

        madd_projector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(AddProjector.this);
                View mview=getLayoutInflater().inflate(R.layout.add_projector_dialog,null);

                final TextInputLayout create_projector=(TextInputLayout) mview.findViewById(R.id.create_projector);
                Button save_projector = (Button) mview.findViewById(R.id.save_projector);
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

                save_projector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String creat_new_projector = create_projector.getEditText().getText().toString();

                        if(TextUtils.isEmpty(creat_new_projector)) {
                            create_projector.setError("Projector name is empty");
                            return;
                        }

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("projector name",""+creat_new_projector);
                        hashMap.put("Status","no");

                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Projector");
                        ref.child(creat_new_projector).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                l1.setVisibility(View.GONE);
                                success_message.setVisibility(View.VISIBLE);

                            }
                        });
                    }
                });

            }
        });


    }
}