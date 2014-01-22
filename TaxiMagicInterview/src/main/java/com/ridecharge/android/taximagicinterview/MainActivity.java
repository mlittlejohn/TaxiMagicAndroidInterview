package com.ridecharge.android.taximagicinterview;

import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MainActivity extends android.support.v4.app.FragmentActivity
{

    private GoogleMap gooMap;
    private SupportMapFragment mapFragment;
    private Geocoder geoCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment));
        geoCoder = new Geocoder(this, Locale.getDefault());
        setupGoogleMap();
    }


    private void setupGoogleMap()
    {
        gooMap = mapFragment.getMap();
        if(gooMap != null)
        {
            gooMap.setMyLocationEnabled(true);
            gooMap.getUiSettings().setCompassEnabled(false);
        }
    }
}
