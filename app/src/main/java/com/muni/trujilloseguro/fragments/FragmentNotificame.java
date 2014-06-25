package com.muni.trujilloseguro.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.muni.trujilloseguro.app.MenuActivity;
import com.muni.trujilloseguro.app.R;

/**
 * Created by Carlos-cs on 17/06/14.
 */
public class FragmentNotificame extends Fragment {

    private static View view;
    private static GoogleMap mMap;
    private static final LatLng Trujillo = new LatLng(-8.111944,-79.028889);


    MapView m;

    public FragmentNotificame() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_layout_notificame, container, false);

        m = (MapView) v.findViewById(R.id.mapView);
        m.onCreate(savedInstanceState);

        mMap = m.getMap();
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-8.111944,-79.028889), 13);
        mMap.animateCamera(cameraUpdate);


        return v;
    }

    @Override
    public void onResume() {
        m.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        m.onLowMemory();
    }
}
