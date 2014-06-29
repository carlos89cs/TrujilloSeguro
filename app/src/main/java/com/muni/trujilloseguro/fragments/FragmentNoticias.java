package com.muni.trujilloseguro.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muni.trujilloseguro.app.R;

/**
 * Created by Carlos-cs on 29/06/2014.
 */
public class FragmentNoticias extends Fragment {



    public FragmentNoticias() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_noticias, container,false);

        return view;
    }




}
