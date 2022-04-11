package com.example.classroommanagementsystemcms.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.classroommanagementsystemcms.Adapter.CourseListAdapter;
import com.example.classroommanagementsystemcms.HelperClass.TeacherCourseList;
import com.example.classroommanagementsystemcms.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherDashboard extends AppCompatActivity {

    private RecyclerView courselist;
    private ArrayList<TeacherCourseList> dataModalArrayList;
    private CourseListAdapter dataRVAdapter;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        courselist = findViewById(R.id.courses_horizontal_view);

        db = FirebaseDatabase.getInstance().getReference();

        dataModalArrayList = new ArrayList<>();
        courselist.setHasFixedSize(true);

        courselist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        dataRVAdapter = new CourseListAdapter(dataModalArrayList, this);


        courselist.setAdapter(dataRVAdapter);

        loadrecyclerViewData();
    }

    private void loadrecyclerViewData() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Course").child("2k18");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataModalArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    TeacherCourseList model = dataSnapshot1.getValue(TeacherCourseList.class);
                    dataModalArrayList.add(model);
                    dataRVAdapter = new CourseListAdapter(dataModalArrayList, TeacherDashboard.this);
                    courselist.setAdapter(dataRVAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}