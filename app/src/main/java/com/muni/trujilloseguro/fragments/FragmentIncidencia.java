package com.muni.trujilloseguro.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muni.trujilloseguro.app.R;

/**
 * Created by Fabio on 24/06/2014.
 */
public class FragmentIncidencia extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_incidencia, container,false);

        return view;
    }
}
