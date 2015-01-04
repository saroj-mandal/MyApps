package com.tonny.myapps.expansecalculator.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.ViewExpenseHistory;
import com.tonny.myapps.expansecalculator.adapter.ExpanseListAdapter;
import com.tonny.myapps.expansecalculator.helper.ExpenseHelper;
import com.tonny.myapps.expansecalculator.helper.MasterObject;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

/**
 * Created by Tonny on 07-09-2014.
 */
public class ExpenseListFragment extends Fragment {
    ListView existingExpanses;
    ExpenseDBManager expenseDBManager;
    MasterObject masterObject = MasterObject.getInstance();
    ExpenseHelper expenseHelper = ExpenseHelper.getInstance();
    View expenseListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        expenseListView = inflater.inflate(R.layout.expanse_fragment_layout, container, false);
        existingExpanses = (ListView) expenseListView.findViewById(R.id.lvExistingExpanse);
        FetchExpanseListAsyncTask fetchExpanseListAsyncTask = new FetchExpanseListAsyncTask();
        fetchExpanseListAsyncTask.execute();
        return expenseListView;
    }

    private class FetchExpanseListAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = expenseHelper.showProgressBar(getActivity());
        }

        @Override
        protected Void doInBackground(Void... params) {
            expenseDBManager = new ExpenseDBManager(getActivity());
            masterObject.setExpenseList(expenseDBManager.getAllExpenseList());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (masterObject.getExpenseList().size() > 0) {
                TextView expenseHomeHeader = (TextView) expenseListView.findViewById(R.id.tvExpanseHomeHeader);
                if (null != expenseHomeHeader) {
                    ((RelativeLayout) expenseHomeHeader.getParent()).removeView(expenseHomeHeader);
                }
            }
            ExpanseListAdapter expanseListAdapter = new ExpanseListAdapter(expenseListView.getContext(), R.layout.activity_expanse_item_fg_layout, masterObject.getExpenseList());
            existingExpanses.setAdapter(expanseListAdapter);
            existingExpanses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent viewHistoryIntent = new Intent(adapterView.getContext(), ViewExpenseHistory.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("expenseId", masterObject.getExpenseList().get(position).getId());
                    viewHistoryIntent.putExtras(bundle);
                    startActivity(viewHistoryIntent);
                }
            });
            expenseHelper.hideProgressBar(progressDialog);
        }
    }
}
