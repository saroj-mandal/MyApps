package com.tonny.myapps.expansecalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tonny on 07-09-2014.
 */
public class PersonsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ExpansesView = inflater.inflate(R.layout.persons_fragment_layout, container, false);
        return ExpansesView;

    }
}
