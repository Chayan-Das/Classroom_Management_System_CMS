package com.example.classroommanagementsystemcms.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.classroommanagementsystemcms.HelperClass.StudentHelperClass;
import com.example.classroommanagementsystemcms.R;
import com.example.classroommanagementsystemcms.Staff.Create.Roommodel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addkey extends AppCompatActivity {

    TextInputLayout textInputEditText;
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1;
    Button button;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth fAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addkey);

        String[] type = new String[] {"Classroom","Laboratory"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(
                this,
                R.layout.spinner_background,type
        );

        autoCompleteTextView = findViewById(R.id.room_type);
        autoCompleteTextView.setAdapter(adapter);

        String[] building = new String[] {"Bangabandhu Sheikh Mujib Academic Building","Central Library Building","Administrative Building"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(
                this,
                R.layout.spinner_background,building
        );

        autoCompleteTextView1 = findViewById(R.id.building_name);
        autoCompleteTextView1.setAdapter(adapter1);

        textInputEditText=findViewById(R.id.room_name_add);
        button=findViewById(R.id.create_room);

        fAuth= FirebaseAuth.getInstance();

        reference=FirebaseDatabase.getInstance().getReference("Rooms");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomname = textInputEditText.getEditText().getText().toString();
                String type = autoCompleteTextView.getText().toString();
                String building = autoCompleteTextView1.getText().toString();

                if(TextUtils.isEmpty(roomname)) {
                    textInputEditText.setError("Enter a room no.");
                    return;
                }

                addinfo();

            }
        });



    }

    private void addinfo() {
        String roomname = textInputEditText.getEditText().getText().toString();
        String type = autoCompleteTextView.getText().toString();
        String building = autoCompleteTextView1.getText().toString();
        String status = "empty";


        if (!TextUtils.isEmpty(roomname)){

            String id = reference.push().getKey();
            Roommodel roommodel= new Roommodel(roomname,type,building,status);

            reference.child(id).setValue(roommodel);

            onBackPressed();

        }
        else {
            Toast.makeText(this, "Enter Room No. First", Toast.LENGTH_LONG).show();
        }

    }
}