package com.example.classroommanagementsystemcms.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classroommanagementsystemcms.R;

public class StudentProfile extends AppCompatActivity {

    EditText my_year,my_phone;
    ImageView pro_image;
    TextView my_name,my_id,my_batch,my_email;
    ImageButton backBtn;
    Button update_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        my_year = findViewById(R.id.my_year);
        my_phone = findViewById(R.id.my_phone);
        my_name = findViewById(R.id.my_name);
        my_id = findViewById(R.id.my_id);
        pro_image = findViewById(R.id.pro_image);
        my_batch = findViewById(R.id.my_batch);
        my_email = findViewById(R.id.my_email);
        backBtn = findViewById(R.id.backBtn);






    }
}