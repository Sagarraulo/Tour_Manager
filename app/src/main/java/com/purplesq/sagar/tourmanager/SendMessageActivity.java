package com.purplesq.sagar.tourmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView message;
    private String snumberlist="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ArrayList<Student> studentlist = new ArrayList<>();
        String slist= "";



        Bundle bundle=getIntent().getExtras();
        studentlist =bundle.getParcelableArrayList("studentlist");
        if(studentlist !=null) {
            for (Student student : studentlist) {
                slist = slist.concat(student.name + ";");
                snumberlist = snumberlist.concat(student.phone + ",");
            }
            TextView reciever = (TextView) findViewById(R.id.student_list);
            message = (TextView) findViewById(R.id.message_edittext);
            Button sendmessage = (Button) findViewById(R.id.send_message_button);
            Button cancel = (Button) findViewById(R.id.cancel_button);
            sendmessage.setOnClickListener(this);
            cancel.setOnClickListener(this);
            reciever.setText(slist);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send_message_button)
            sendmessage();
        else
            cancelmessage();
    }
    public void sendmessage(){

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + snumberlist));
        sendIntent.putExtra("sms_body", message.getText().toString());
        startActivityForResult(sendIntent, 101);
    }
    public void cancelmessage(){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        new ShowDialogFragment().show(getSupportFragmentManager(), "sendmsg");
    }
}
