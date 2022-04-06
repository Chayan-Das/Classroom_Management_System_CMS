package com.example.classroommanagementsystemcms.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.classroommanagementsystemcms.R;

public class StudentDashboard extends AppCompatActivity {

    RelativeLayout r1,r2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        r1=findViewById(R.id.app_bar);


    }
}