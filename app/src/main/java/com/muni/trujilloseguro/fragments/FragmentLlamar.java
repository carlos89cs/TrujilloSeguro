package com.muni.trujilloseguro.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.muni.trujilloseguro.adapters.InstitucionAdapter;
import com.muni.trujilloseguro.app.R;
import com.muni.trujilloseguro.models.InstitucionItem;

/**
 * Created by Carlos-cs on 24/06/2014.
 */
public class FragmentLlamar  extends Fragment
{
    public FragmentLlamar(){

    }

    ListView listainstituciones;
    ImageButton btnllamar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_llamar, container,false);
        listainstituciones = (ListView) view.findViewById(R.id.Lista);

        InstitucionItem[] args = new InstitucionItem[]
        {
           new InstitucionItem("Serenazgo MPT","233333",R.drawable.serenazgo,0),
           new InstitucionItem("Bomberos","205632",R.drawable.bomberos,1),
           new InstitucionItem("Comiseria Ayacucho","291436",R.drawable.comi_ayacucho,2),
           new InstitucionItem("Comiseria La Noria","217433",R.drawable.comi_noria,3),
           new InstitucionItem("Comiseria San Andres","296821",R.drawable.comi_sanandres,4),
           new InstitucionItem("Radio Patrulla","221908",R.drawable.comi_ayacucho,5),
        };

        InstitucionAdapter adap = new InstitucionAdapter(getActivity(),args);
        listainstituciones.setAdapter(adap);






        return view;
    }

    private class MyPhoneListener extends PhoneStateListener {

        private boolean onCall = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // phone ringing...
                    Toast.makeText(getActivity(), incomingNumber + " calls you",
                            Toast.LENGTH_LONG).show();
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // one call exists that is dialing, active, or on hold
                    Toast.makeText(getActivity(), "on call...",
                            Toast.LENGTH_LONG).show();
                    //because user answers the incoming call
                    onCall = true;
                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                    // in initialization of the class and at the end of phone call

                    // detect flag from CALL_STATE_OFFHOOK
                    if (onCall == true) {
                        Toast.makeText(getActivity(), "restart app after call",
                                Toast.LENGTH_LONG).show();

                        // restart our application
                        Intent restart = getActivity().getPackageManager().
                                getLaunchIntentForPackage(getActivity().getPackageName());
                        restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(restart);

                        onCall = false;
                    }
                    break;
                default:
                    break;
            }

        }
    }

}




