package com.muni.trujilloseguro.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.muni.trujilloseguro.app.MenuActivity;
import com.muni.trujilloseguro.app.R;
import com.muni.trujilloseguro.gps.GPSTracker;
import com.muni.trujilloseguro.gps.ReverseGeocodingTask;

import java.io.IOException;
import java.util.List;

/**
 * Created by Fabio on 24/06/2014.
 */
public class FragmentIncidencia extends Fragment implements View.OnClickListener {

    View view;
    private Spinner tipoincidencias;
    private ImageView foto;
    EditText miDireccion;
    String miDir;
    private LatLng miPos;
    private Button btnShowLocation,btnTomaFoto;
    final static int REQUEST_PHOTO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_layout_incidencia, container,false);

        inicializarComponentes();
        CargarPosicion();

        btnShowLocation.setOnClickListener(this);
        btnTomaFoto.setOnClickListener(this);

        return view;
    }
    private void CargarPosicion() {

        GPSTracker gps = new GPSTracker(getActivity());
        if(gps.canGetLocation()){
            if(miDir == null){
                miPos = new LatLng(gps.getLatitude(), gps.getLongitude());
//                Toast.makeText(getActivity(), "Hey!!  Tu Locación es - \nLat: " + miPos.latitude + "\nLong: " + miPos.longitude, Toast.LENGTH_LONG).show();
                ReverseGeocodingTask AT = new ReverseGeocodingTask(view.getContext());
                miDir = AT.doInBackground(miPos);
            }
            miDireccion.setText(miDir);
          }else{
            gps.showSettingsAlert();
        }
    }

    public void inicializarComponentes()
    {
        btnShowLocation = (Button) view.findViewById(R.id.btnShowLocation);
        btnTomaFoto = (Button) view.findViewById(R.id.btnCapturaFoto);

        foto = (ImageView) view.findViewById(R.id.imgPreview);
        //Spinner(Tipo de Incidencias)
        tipoincidencias = (Spinner)view.findViewById(R.id.spinner_tipo);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.tipoincidencias,android.R.layout.simple_list_item_1);
        tipoincidencias.setAdapter(adapter);

        //Dirección
        miDireccion = (EditText) view.findViewById(R.id.tv_direccion);


    }

    @Override
    public void onClick(View v) {

        int id;
        id = v.getId();
        switch (id) {
            case R.id.btnShowLocation:
                FragmentMapaInicidencia fragment = new FragmentMapaInicidencia();
                Bundle args = new Bundle();
                args.putDouble("milat", miPos.latitude);
                args.putDouble("milng", miPos.longitude);
                args.putString("Address", miDireccion.getText().toString());
                fragment.setArguments(args);

                FragmentManager ft = getActivity().getSupportFragmentManager();
                ft.beginTransaction().replace(R.id.fragment_incidencia, fragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                ft.executePendingTransactions();
                btnShowLocation.setEnabled(false);
                break;

            case R.id.btnCapturaFoto:
                Intent in = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(in, REQUEST_PHOTO);
                break;

            default:
                break;
        }
    }

}
