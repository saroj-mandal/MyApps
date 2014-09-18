package com.tonny.myapps.expansecalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tonny.myapps.expansecalculator.adapter.ExpanseListAdapter;
import com.tonny.myapps.expansecalculator.beans.Expanse;
import com.tonny.myapps.expansecalculator.utills.ExpanseDBManager;

import java.util.List;

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
        existingExpanses = (ListView) expanseListView.findViewById(R.id.lvExistingExpanse);
        /*String[] dbColumns = new String[]{"name", "description"};
        int[] viewIds = new int[]{R.id.tvViewExpanseName, R.id.tvViewExpanseDesc};
        Cursor cursor = expanseDBManager.getExpanseCursor();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.activity_expanse_item_fg_layout, cursor, dbColumns, viewIds);
        existingExpanses.setAdapter(simpleCursorAdapter);
        existingExpanses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent viewHistoryIntent = new Intent(adapterView.getContext(), ViewExpanseHistory.class);
                startActivity(viewHistoryIntent);
            }
        });*/
        List<Expanse> expanseList = expanseDBManager.getExpanse();
        ExpanseListAdapter expanseListAdapter = new ExpanseListAdapter(expanseListView.getContext(), R.layout.activity_expanse_item_fg_layout, expanseList);
        existingExpanses.setAdapter(expanseListAdapter);
        existingExpanses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent viewHistoryIntent = new Intent(adapterView.getContext(), ViewExpanseHistory.class);
                startActivity(viewHistoryIntent);
            }
        });
        return expanseListView;
    }
}
