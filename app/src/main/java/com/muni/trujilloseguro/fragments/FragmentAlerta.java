package com.muni.trujilloseguro.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.muni.trujilloseguro.app.R;

import android.support.v4.app.Fragment;
import android.widget.Toast;


/**
 * Created by Carlos on 05/06/14.
 */
public class FragmentAlerta extends Fragment {


    public FragmentAlerta() {

    }

    Button btnContacto;
    ImageButton btnAlerta;
    TextView textPhone;

    private static final int RQS_PICKCONTACT = 2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_alerta, container,false);

        btnContacto = (Button)view.findViewById(R.id.buttoncontacto);
        btnAlerta = (ImageButton)view.findViewById(R.id.btn_alerta);
        textPhone = (TextView) view.findViewById(R.id.phone);
        Spinner spn = (Spinner) view.findViewById(R.id.spinner_contactos);

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.miscontactos,android.R.layout.simple_list_item_1);
        spn.setAdapter(adapter);

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
                Intent intentPickContact = new Intent(Intent.ACTION_PICK, uriContact);
                getActivity().startActivityForResult(intentPickContact, RQS_PICKCONTACT);
            }
        });

        btnAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Se envió SMS a la PNP y contactos", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}
