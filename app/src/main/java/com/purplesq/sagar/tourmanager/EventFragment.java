package com.purplesq.sagar.tourmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventFragment extends Fragment implements View.OnClickListener {
    private Event event;

    public static EventFragment newInstance(Event event) {
        Bundle args = new Bundle();
        args.putParcelable("event",event);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        event=bundle.getParcelable("event");
        TextView eventname=(TextView)view.findViewById(R.id.eventname_textview);
        TextView eventdate=(TextView)view.findViewById(R.id.event_date);
        TextView eventday=(TextView)view.findViewById(R.id.event_day);
        TextView eventoverview=(TextView)view.findViewById(R.id.event_overview);
        TextView location=(TextView)view.findViewById(R.id.location);
        location.setOnClickListener(this);
        if(event!=null) {
            eventname.setText(event.name);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.US);
            eventdate.setText(sdf.format(new Date(event.itineraries[0].date)));
            eventday.setText(String.format("%d Day",event.days));
            eventoverview.setText(event.overview);
            LinearLayout itinerariesdetail=(LinearLayout)view.findViewById(R.id.itineraies_detail);
            ItineraryAdapter adapter=new ItineraryAdapter(this,event.itineraries);
            for(int i=0;i<event.itineraries.length;i++)
                itinerariesdetail.addView(adapter.getView(i,null,itinerariesdetail));
            ImageView eventimage=(ImageView)view.findViewById(R.id.event_image);
            Picasso.with(getContext()).load(event.thumbnail).fit().into(eventimage);

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getActivity(),LocationsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelableArray("location",event.itineraries);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
