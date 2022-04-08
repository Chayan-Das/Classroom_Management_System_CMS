package com.example.classroommanagementsystemcms;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class BookClass extends AppCompatActivity {

    TextInputLayout Topic;
    Calendar calendar;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_class);

        Topic=findViewById(R.id.topic);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        
        Calendar calendar = Calendar.getInstance();

        String date = dateFormat.format(calendar.getTime());

        Topic.getEditText().setText(date);


    }
}