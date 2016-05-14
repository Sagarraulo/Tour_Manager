package com.purplesq.sagar.tourmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private Itinerary[] mitineraries;
    private LayoutInflater mlayoutInflater;
    private LocationsActivity mlocationsActivity;
    private int pos=-1;

    public LocationAdapter(LocationsActivity locationsActivity, Itinerary[] itineraries) {
        mitineraries=itineraries;
        mlayoutInflater=LayoutInflater.from(locationsActivity);
        mlocationsActivity=locationsActivity;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(mlayoutInflater.inflate(R.layout.location_row,parent,false));
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, final int position) {
        holder.time.setText(mitineraries[position].start_time);
        holder.title.setText(mitineraries[position].title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlocationsActivity.showLocation(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mitineraries.length;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView time;
        public LocationViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.location_title);
            time= (TextView) itemView.findViewById(R.id.location_time);
        }
    }


}
