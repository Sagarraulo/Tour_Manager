package com.purplesq.sagar.tourmanager;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    public final Student[] students;
    public final LayoutInflater mlayoutinflater;
    public Boolean[] selectStudent;

    public StudentAdapter(StudentFragment studentFragment, Student[] students) {
        this.students = students;
        this.mlayoutinflater = LayoutInflater.from(studentFragment.getContext());
        selectStudent = new Boolean[students.length];
        for (int i = 0; i < students.length; i++)
            selectStudent[i] = false;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=mlayoutinflater.inflate(R.layout.student_row, parent, false);
        return new StudentViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final StudentViewHolder holder,final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStudent[position]=!selectStudent[position];
                holder.selectbox.setChecked(selectStudent[position]);
            }
        });
        holder.nameview.setText(students[position].name);
        holder.emailview.setText("Email: "+students[position].email);
        holder.phoneview.setText("Phone: "+students[position].phone);
        holder.selectbox.setChecked(selectStudent[position]);
    }

    @Override
    public int getItemCount() {
        return students.length;
    }

    public void selectAll(boolean select) {
            for (int i = 0; i < students.length; i++) {
                selectStudent[i] = select;
            }
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{
        public final TextView nameview;
        public final TextView emailview;
        public final TextView phoneview;
        public final CheckBox selectbox;

        public StudentViewHolder(View itemView) {
            super(itemView);
            nameview = (TextView) itemView.findViewById(R.id.student_name_textview);
            emailview = (TextView) itemView.findViewById(R.id.student_email_textview);
            phoneview = (TextView) itemView.findViewById(R.id.student_phone_textview);
            selectbox = (CheckBox) itemView.findViewById(R.id.student_select_checkbox);
            selectbox.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int i = this.getAdapterPosition();
            selectStudent[i] = isChecked;
        }
    }
}
