package com.purplesq.sagar.tourmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class StudentFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {
    private StudentAdapter studentadapter;
    private Student[] studentlist;
    public static StudentFragment newInstance(Student[] students) {
        Bundle args = new Bundle();
        args.putParcelableArray("student",students);
        StudentFragment fragment = new StudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_student, container, false);    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        studentlist=(Student[])bundle.getParcelableArray("student");
        CheckBox selectAll=(CheckBox)view.findViewById(R.id.select_all_checkbox);
        Button sendbutton=(Button)view.findViewById(R.id.send_button);
        sendbutton.setOnClickListener(this);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.student_recycler_view);


        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        selectAll.setOnCheckedChangeListener(this);
        studentadapter=new StudentAdapter(this,studentlist);
        recyclerView.setAdapter(studentadapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            studentadapter.selectAll(true);
        else
            studentadapter.selectAll(false);

    }

    @Override
    public void onClick(View v) {
        sendmessage();
    }
    public void sendmessage(){

        ArrayList<Student> studentname=new ArrayList<>();
        for(int i=0;i<studentlist.length;i++)
            if(studentadapter.selectStudent[i])
                studentname.add(studentlist[i]);
                Intent intent = new Intent(getActivity(), SendMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("studentlist", studentname);
                intent.putExtras(bundle);
                startActivity(intent);
    }
}
