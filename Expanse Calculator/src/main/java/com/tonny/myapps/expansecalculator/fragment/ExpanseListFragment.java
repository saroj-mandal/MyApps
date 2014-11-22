package com.tonny.myapps.expansecalculator.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.ViewExpanseHistory;
import com.tonny.myapps.expansecalculator.adapter.ExpanseListAdapter;
import com.tonny.myapps.expansecalculator.beans.Expense;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

import java.util.List;

/**
 * Created by Tonny on 07-09-2014.
 */
public class ExpanseListFragment extends Fragment {
    SharedPreferences.Editor shPrefEditor;
    ListView existingExpanses;
    ExpenseDBManager expenseDBManager;
    List<Expense> expenseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View expanseListView = inflater.inflate(R.layout.expanse_fragment_layout, container, false);
        expenseDBManager = new ExpenseDBManager(getActivity());
        existingExpanses = (ListView) expanseListView.findViewById(R.id.lvExistingExpanse);
        expenseList = expenseDBManager.getAllExpenseList();
        ExpanseListAdapter expanseListAdapter = new ExpanseListAdapter(expanseListView.getContext(), R.layout.activity_expanse_item_fg_layout, expenseList);
        existingExpanses.setAdapter(expanseListAdapter);
        existingExpanses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent viewHistoryIntent = new Intent(adapterView.getContext(), ViewExpanseHistory.class);
                Bundle bundle = new Bundle();
                bundle.putLong("expenseId", expenseList.get(position).getId());
                viewHistoryIntent.putExtras(bundle);
                startActivity(viewHistoryIntent);
            }
        });
        return expanseListView;
    }
}
