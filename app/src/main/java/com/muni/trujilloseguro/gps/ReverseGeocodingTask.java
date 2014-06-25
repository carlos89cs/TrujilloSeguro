package com.muni.trujilloseguro.gps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Fabio on 25/06/2014.
 */
public class ReverseGeocodingTask extends AsyncTask<LatLng, Void, String> {

    private Context mContext;
    private EditText miDireccion;
    private  String addressText;
    public ReverseGeocodingTask(Context context, EditText miDireccion){
        super();
        mContext = context;
        this.miDireccion = miDireccion;

    }

    @Override
    protected String doInBackground(LatLng... params) {
        Geocoder geocoder = new Geocoder(mContext);
        double latitude = params[0].latitude;
        double longitude = params[0].longitude;

        List<Address> addresses = null;
        addressText = "";

        try {
            addresses = geocoder.getFromLocation(latitude, longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses != null && addresses.size() > 0 ){
            Address address = addresses.get(0);
            addressText = String.format("%s",
                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "");
        }

        return addressText;
    }

    @Override
    protected void onPostExecute(String addressText) {
        Toast.makeText(mContext, addressText, Toast.LENGTH_LONG).show();
        miDireccion.setText(addressText.toString());
      //  this.addressText = addressText;
    }

}
