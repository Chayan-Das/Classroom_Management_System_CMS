package com.example.classroommanagementsystemcms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroommanagementsystemcms.HelperClass.AddTeacherHelperClass;
import com.example.classroommanagementsystemcms.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class Teacherlist extends RecyclerView.Adapter<Teacherlist.ViewHolder>{

    private ArrayList<AddTeacherHelperClass> dataModalArrayList;
    private Context context;

    public Teacherlist(ArrayList<AddTeacherHelperClass> dataModalArrayList, Context context) {
        this.dataModalArrayList = dataModalArrayList;
        this.context = context;
    }

    public Teacherlist(@NonNull FirebaseRecyclerOptions<AddTeacherHelperClass> options) {
        
    }


    @NonNull
    @Override
    public Teacherlist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Teacherlist.ViewHolder(LayoutInflater.from(context).inflate(R.layout.horizontal_teacher_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Teacherlist.ViewHolder holder, int position) {

        final AddTeacherHelperClass teacherCourseList = dataModalArrayList.get(position);
        holder.teacher_name.setText(teacherCourseList.getT_name());
        holder.teacher_id.setText(teacherCourseList.getT_id());
        holder.teacher_designation.setText(teacherCourseList.getT_designation());
        holder.teacher_email.setText(teacherCourseList.getT_email());
        holder.teacher_phone.setText(teacherCourseList.getT_phone());
        holder.department.setText(teacherCourseList.getDepartmnet());

    }

    @Override
    public int getItemCount() {
        return dataModalArrayList.size();
    }

    public void startListening() {
    }

    public void stopListening() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView teacher_name;
        private final TextView teacher_designation;
        private final TextView teacher_id;
        private final TextView teacher_phone;
        private final TextView teacher_email;
        private final TextView department;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teacher_name = itemView.findViewById(R.id.t_name);
            teacher_designation = itemView.findViewById(R.id.t_designation);
            teacher_id = itemView.findViewById(R.id.t_id);
            teacher_phone = itemView.findViewById(R.id.t_phone);
            teacher_email = itemView.findViewById(R.id.t_email);
            department = itemView.findViewById(R.id.department);

        }
    }
}
