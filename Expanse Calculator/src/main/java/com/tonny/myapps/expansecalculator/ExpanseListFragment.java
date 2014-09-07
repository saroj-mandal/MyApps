package com.tonny.myapps.expansecalculator;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.tonny.myapps.expansecalculator.utills.ExpanseDBManager;

/**
 * Created by Tonny on 07-09-2014.
 */
public class ExpanseListFragment extends Fragment {
    SharedPreferences.Editor shPrefEditor;
    ListView existingExpanses;
    ExpanseDBManager expanseDBManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View expanseListView = inflater.inflate(R.layout.expanse_fragment_layout, container, false);
        expanseDBManager = new ExpanseDBManager(getActivity());
        String[] dbColumns = new String[]{"name", "description"};
        int[] viewIds = new int[]{R.id.tvViewExpanseName, R.id.tvViewExpanseDesc};
        existingExpanses = (ListView) expanseListView.findViewById(R.id.lvExistingExpanse);
        Cursor cursor = expanseDBManager.getExpanseCursor();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.activity_expanse_item_fg_layout, cursor, dbColumns, viewIds);
        existingExpanses.setAdapter(simpleCursorAdapter);
        return expanseListView;

    }
}
