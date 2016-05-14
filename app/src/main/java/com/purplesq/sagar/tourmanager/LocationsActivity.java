package com.purplesq.sagar.tourmanager;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationsActivity extends AppCompatActivity implements OnMapReadyCallback,LocationListener{
    private LocationManager manager;
    private GoogleMap map;
    private Itinerary[] itineraries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getExtras();
        Parcelable[] parcelable= bundle.getParcelableArray("location");
        itineraries=new Itinerary[parcelable.length];
        for(int i=0;i<parcelable.length;i++)
            itineraries[i]=(Itinerary)parcelable[i];

        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);

        manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.location_recyclerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        LocationAdapter adapter=new LocationAdapter(this,itineraries);
        recyclerView.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.mapcontainer, mapFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.location_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (map != null) {
            int maptype;
            switch (item.getItemId()) {
                case R.id.menu_item_hybrid:
                    maptype =GoogleMap.MAP_TYPE_HYBRID;break;
                case R.id.menu_item_normal:
                    maptype =GoogleMap.MAP_TYPE_NORMAL;break;
                case R.id.menu_item_terrain:
                    maptype =GoogleMap.MAP_TYPE_TERRAIN;break;
                case android.R.id.home:finish();
                default:
                    maptype =GoogleMap.MAP_TYPE_SATELLITE;break;
            }
            map.setMapType(maptype);
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        map.setMyLocationEnabled(true);
        //map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setAllGesturesEnabled(true);

        try {
            for(Itinerary it:itineraries) {
                double lat = it.location.lat;
                double lng = it.location.lng;
                LatLng latLong = new LatLng(lat, lng);

                MarkerOptions options = new MarkerOptions()
                        .anchor(0.75f, 0.75f)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc))
                        .title(it.title)
                        .snippet(it.description)
                        .position(new LatLng(lat, lng));
                map.addMarker(options);

            }

        } catch (Exception e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_window, null);
                TextView markertitle = (TextView) v.findViewById(R.id.markertitle);
                TextView markerdesc = (TextView) v.findViewById(R.id.markerdesc);

                markertitle.setText(marker.getTitle());
                markerdesc.setText(marker.getSnippet());

                return v;
            }
        });

    }
    public void showLocation(int pos){
        double lat = itineraries[pos].location.lat;
        double lng = itineraries[pos].location.lng;
        LatLng latLong = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLong, 15);
        map.moveCamera(update);
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}
