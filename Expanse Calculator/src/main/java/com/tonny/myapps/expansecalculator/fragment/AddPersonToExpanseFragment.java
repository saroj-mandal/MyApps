package com.tonny.myapps.expansecalculator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonny.myapps.expansecalculator.R;

/**
 * Created by Tonny on 07-09-2014.
 */
public class AddPersonToExpanseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View addPersonView = inflater.inflate(R.layout.add_person_to_expanse_fragment_layout, container, false);
        return addPersonView;
    }
}
