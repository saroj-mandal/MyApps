package com.tonny.myapps.expansecalculator.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.beans.DailyRecords;
import com.tonny.myapps.expansecalculator.beans.Profile;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tonny on 07-09-2014.
 */
public class RecordsHistoryFragment extends Fragment {
    TableLayout recordHistoryLayout;
    ExpenseDBManager expenseDBManager;
    List<DailyRecords> recordsList = new ArrayList<DailyRecords>();
    long expenseId;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recordsHistoryView = inflater.inflate(R.layout.view_records_fragment_layout, container, false);
        Intent viewHistoryIntent = getActivity().getIntent();
        Bundle expanseDetailsBundle = viewHistoryIntent.getExtras();
        expenseId = expanseDetailsBundle.getLong("expenseId");
        expenseDBManager = new ExpenseDBManager(getActivity());
        recordHistoryLayout = (TableLayout) recordsHistoryView.findViewById(R.id.tlRecordHistory);
        buildTable();
        return recordsHistoryView;
    }

    private void buildTable() {
        List<Profile> participantList = expenseDBManager.getParticipantList(expenseId);
        recordsList = expenseDBManager.getRecordHistory(expenseId);
        for (DailyRecords dailyRecord : recordsList) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            for (Profile profile : participantList) {
                TextView textView = new TextView(getActivity());
                textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(0, 5, 0, 5);
                textView.setText(String.valueOf(dailyRecord.getAmount()));
                tableRow.addView(textView);
            }
            recordHistoryLayout.addView(tableRow);
        }
    }

    private class MyAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            recordHistoryLayout.removeAllViews();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            buildTable();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            buildTable();
            progressDialog.dismiss();
        }
    }
}
