package com.purplesq.sagar.tourmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CodeActivity extends AppCompatActivity implements View.OnClickListener, Callback<Events> {
    private EditText editText;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        editText=(EditText)findViewById(R.id.code_edittext);
        Button button=(Button)findViewById(R.id.event_button);
        button.setOnClickListener(this);
        progressBar= (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

    } @Override
      public void onClick(View v) {
        eventDetails();
    }

    public void eventDetails(){

        progressBar.setVisibility(View.VISIBLE);
        String id= editText.getText().toString();
        DownloadService service = EventDownloadAdapter.getretrofitbuilder();
        Call<Events> eventscall = service.fetchEvents();
        eventscall.enqueue(this) ;

    }

    @Override
    public void onResponse(Response<Events> response, Retrofit retrofit) {
        Events eventlist = response.body();
        progressBar.setVisibility(View.INVISIBLE);
        for (Event event: eventlist.events) {
            if (event.id.equals(editText.getText().toString())){
                Bundle bundle=new Bundle();
                bundle.putParcelable("event", event);
                Intent intent=new Intent(this,EventDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
                Snackbar.make(editText.getRootView(), "Wrong id",Snackbar.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Throwable t) {
        progressBar.setVisibility(View.INVISIBLE);
        Snackbar.make(editText.getRootView(), "Network Error",Snackbar.LENGTH_SHORT).show();

    }
}
