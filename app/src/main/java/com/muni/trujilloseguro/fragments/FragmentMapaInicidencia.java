package com.muni.trujilloseguro.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.muni.trujilloseguro.app.R;
import com.muni.trujilloseguro.gps.ReverseGeocodingTask;

/**
 * Created by Fabio on 25/06/2014.
 */
public class FragmentMapaInicidencia extends Fragment implements
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener {

    private static View view;
    private static GoogleMap mMap;
    private static LatLng miPos = null;
    private static String miDir = null;
    private static Marker miMarker = null;
    private static LatLng PosFinal = null;

    private FragmentIncidencia FragmentPadre;

    public FragmentMapaInicidencia(FragmentIncidencia FragmentPadre){

        this.FragmentPadre = FragmentPadre;
    }

    MapView m;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        view = inflater.inflate(R.layout.fragment_layout_mapa_incidencia, container, false);

        m = (MapView) view.findViewById(R.id.mapView_inicidencia);
        m.onCreate(savedInstanceState);

            setArguments();
            setupMap();

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Button btnshowmap = (Button) getActivity().findViewById(R.id.btnShowLocation);
                btnshowmap.setEnabled(true);
                EditText dir = (EditText) getActivity().findViewById(R.id.tv_direccion);
                dir.setText(marker.getTitle().toString());
                FragmentPadre.setPosicion(marker.getPosition());
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        return view;
    }
    public void setArguments(){
        Bundle args = getArguments();
        if (args != null) {
            double LATITUDE = args.getDouble("milat");
            double LONGITUDE = args.getDouble("milng");
            miDir = args.getString("Address");
            miPos = new LatLng(LATITUDE, LONGITUDE);
            Toast.makeText(view.getContext(), "Hey!!  Tu Locación es - \nLat: " + miPos.latitude + "\nLong: " + miPos.longitude, Toast.LENGTH_LONG).show();

        }
    }

    private void setupMap(){

        mMap = m.getMap();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);

        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(miPos, 15);
        mMap.animateCamera(cameraUpdate);

        setMarker(miPos, miDir, "Incidencia");

    }

    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        miMarker = mMap.addMarker(new MarkerOptions()
                .visible(true)
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); //Color del marcador

        miMarker.showInfoWindow();

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

    @Override
    public void onMapClick(LatLng latLng) {

        ReverseGeocodingTask AT = new  ReverseGeocodingTask(getActivity().getBaseContext());
        setMarker(latLng,AT.doInBackground(latLng),"");

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("GoogleMapActivity", "onMarkerClick");
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

        ReverseGeocodingTask AT = new ReverseGeocodingTask(getActivity());
        PosFinal = marker.getPosition();
        marker.setTitle(AT.doInBackground(PosFinal));
        marker.showInfoWindow();

    }

}
