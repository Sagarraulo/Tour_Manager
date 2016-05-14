package com.purplesq.sagar.tourmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by lenovo-pc on 09-Jan-16.
 */
public class ItineraryAdapter extends BaseAdapter{
    private Itinerary[] mitineraries;
    private LayoutInflater mlayoutInflater;

    public ItineraryAdapter(EventFragment eventFragment, Itinerary[] itineraries) {
        mitineraries=itineraries;
        mlayoutInflater=LayoutInflater.from(eventFragment.getContext());
    }

    @Override
    public int getCount() {
        return mitineraries.length;
    }

    @Override
    public Object getItem(int position) {
        return mitineraries[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = mlayoutInflater.inflate(R.layout.itinerary_row, parent, false);
        }
        TextView time=(TextView)convertView.findViewById(R.id.itineraies_time);
        TextView title=(TextView)convertView.findViewById(R.id.itineraies_title);
        TextView description=(TextView)convertView.findViewById(R.id.itineraies_description);

        time.setText(mitineraries[position].start_time);
        title.setText(mitineraries[position].title);
        description.setText(mitineraries[position].description);
        return convertView;
    }
}
