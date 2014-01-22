package com.ridecharge.android.taximagicinterview;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends android.support.v4.app.FragmentActivity
{

    private GoogleMap gooMap;
    private SupportMapFragment mapFragment;
    private Geocoder geoCoder;
    String provider;
    Location location;
    LocationManager service;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        service = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Criteria criteria = new Criteria();
        provider = service.getBestProvider(criteria, false);
        location = service.getLastKnownLocation(provider);
        if(location == null){
            location = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        else{
            location.setLatitude(38.79288);
            location.setLongitude(-77.06002);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment));
        geoCoder = new Geocoder(this, Locale.getDefault());
        new updateLocationTask().execute();

    }

    @Override
    public void onResume(){
        super.onResume();
        location = service.getLastKnownLocation(provider);
        if(location == null){
            location = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        setupGoogleMap();
    }

    @Override
    public final void runOnUiThread(Runnable action){
        if(Thread.currentThread())
    }



    private void setupGoogleMap()
    {
        gooMap = mapFragment.getMap();
        if(gooMap != null)
        {
            gooMap.setMyLocationEnabled(true);
            gooMap.getUiSettings().setCompassEnabled(false);
            LatLng markerPosition = new LatLng(location.getLatitude(), location.getLongitude());
            gooMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 15));
            marker = gooMap.addMarker(new MarkerOptions().position(markerPosition).draggable(false));
        }
    }

    private void updateLocation(){
        Random r = new Random();
        int randNum = r.nextInt(5-1)+1;
        Location randomLocation = location;
        randomLocation.setLatitude(location.getLatitude()+randNum);
        randomLocation.setLongitude(location.getLongitude()+randNum);
        if(location.distanceTo(randomLocation)<2000){
            location = randomLocation;
        }
    }

    private class updateLocationTask extends AsyncTask<Void, Void, Void>{

        void Sleep(int ms){
            try{
                Thread.sleep(ms);
            }
            catch(Exception e){

            }
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            while(true){
                updateLocation();
                Sleep(5000);
            }
        }
    }
}
