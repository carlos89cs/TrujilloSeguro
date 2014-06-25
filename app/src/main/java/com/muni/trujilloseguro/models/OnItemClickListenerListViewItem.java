package com.muni.trujilloseguro.models;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.muni.trujilloseguro.app.R;

/**
 * Created by Carlos-cs on 25/06/2014.
 */
public class OnItemClickListenerListViewItem implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Context context = view.getContext();

        TextView txtInstituto = (TextView)view.findViewById(R.id.nombre);

        String listItemText = txtInstituto.getText().toString();

        Toast.makeText(context,"Item" + listItemText ,Toast.LENGTH_SHORT).show();

    }
}
