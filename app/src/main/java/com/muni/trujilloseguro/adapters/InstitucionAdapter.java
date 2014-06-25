package com.muni.trujilloseguro.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.muni.trujilloseguro.app.R;
import com.muni.trujilloseguro.models.InstitucionItem;

/**
 * Created by Carlos-cs on 24/06/2014.
 */
public class InstitucionAdapter extends ArrayAdapter {

    Activity context;
    InstitucionItem [] datos;

    public InstitucionAdapter(Activity context,InstitucionItem [] datos){

        super(context, R.layout.drawer_institucion_item,datos);
        this.datos = datos;
        this.context = context;
    }

    public View getView(int position,View convertView,ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.drawer_institucion_item,null);

        TextView institucion = (TextView)item.findViewById(R.id.nombre);
        institucion.setText(datos[position].getInstitucion());

        TextView telefono = (TextView) item.findViewById(R.id.cargo);
        telefono.setText(datos[position].getTelefono());

        ImageView imagen = (ImageView)item.findViewById(R.id.foto);
        imagen.setImageResource(datos[position].getImg());

        Typeface tvFont = Typeface.createFromAsset(context.getAssets(),"fonts/KaushanScript-Regular.otf");
        Typeface tvFont2 = Typeface.createFromAsset(context.getAssets(),"fonts/Quicksand-Light.otf");

        institucion.setTypeface(tvFont, Typeface.BOLD);
        telefono.setTypeface(tvFont2,Typeface.BOLD);


        return item;

    }



}
