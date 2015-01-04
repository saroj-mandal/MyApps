package com.tonny.myapps.expansecalculator.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.beans.DailyRecords;
import com.tonny.myapps.expansecalculator.beans.Profile;
import com.tonny.myapps.expansecalculator.helper.ExpenseHelper;
import com.tonny.myapps.expansecalculator.helper.MasterObject;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Tonny on 07-09-2014.
 */
public class RecordsHistoryFragment extends Fragment {
    TableLayout recordHistoryLayout;
    ExpenseDBManager expenseDBManager;
    long expenseId;
    MasterObject masterObject = MasterObject.getInstance();
    ExpenseHelper expenseHelper = ExpenseHelper.getInstance();
    View recordsHistoryView;
    boolean isHeader = true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recordsHistoryView = inflater.inflate(R.layout.view_records_fragment_layout, container, false);
        Intent viewHistoryIntent = getActivity().getIntent();
        Bundle expanseDetailsBundle = viewHistoryIntent.getExtras();
        expenseId = expanseDetailsBundle.getLong("expenseId");
        recordHistoryLayout = (TableLayout) recordsHistoryView.findViewById(R.id.tlRecordHistory);
        RecordHistoryAsyncTask recordHistoryAsyncTask = new RecordHistoryAsyncTask();
        recordHistoryAsyncTask.execute();
        return recordsHistoryView;
    }

    private void buildTable() {
        for (DailyRecords dailyRecord : masterObject.getExpenseRecords().get(expenseId)) {
            TextView recordsCap = (TextView) recordsHistoryView.findViewById(R.id.tvRecordsHeader);
            if (null != recordsCap) {
                ((RelativeLayout) recordsCap.getParent()).removeView(recordsCap);
            }
            if (isHeader) {
                isHeader = false;
                TableRow tableHeader = buildHeader();
                recordHistoryLayout.addView(tableHeader);
            }
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(expenseHelper.createTextView(getActivity(), String.valueOf(Math.round(dailyRecord.getAmount())), Color.GREEN));
            for (Profile profile : masterObject.getExpenseParticipant().get(expenseId)) {
                Long amount = Math.round(dailyRecord.getAmount() / dailyRecord.getParticipantIdList().size());
                if (dailyRecord.getParticipantIdList().contains(profile.getId())) {
                    tableRow.addView(expenseHelper.createTextView(getActivity(), String.valueOf(amount), Color.RED));
                } else {
                    tableRow.addView(expenseHelper.createTextView(getActivity(), "-", Color.GREEN));
                }
            }
            recordHistoryLayout.addView(tableRow);
        }
    }

    private TableRow buildHeader() {
        TableRow tableRow = new TableRow(getActivity());
        tableRow.addView(expenseHelper.createTextView(getActivity(), "Amount", Color.BLUE));
        for (Profile profile : masterObject.getExpenseParticipant().get(expenseId)) {
            String headerText = getInitials(profile);
            tableRow.addView(expenseHelper.createTextView(getActivity(), headerText, Color.BLUE));
        }
        return tableRow;
    }


    private String getInitials(Profile profile) {
        StringBuffer headerText = new StringBuffer();
        if (null != profile.getLastName()) {
            headerText.append(profile.getFirstName().charAt(0));
            headerText.append(profile.getLastName().charAt(0));
        } else {
            headerText.append(profile.getFirstName().substring(0, 2));
        }
        return headerText.toString().toUpperCase();
    }

    private class RecordHistoryAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            recordHistoryLayout.removeAllViews();
            progressDialog = expenseHelper.showProgressBar(getActivity());
        }

        @Override
        protected Void doInBackground(Void... params) {
            expenseDBManager = new ExpenseDBManager(getActivity());
            if (null == masterObject.getExpenseParticipant()) {
                masterObject.setExpenseParticipant(new HashMap<Long, List<Profile>>());
                masterObject.getExpenseParticipant().put(expenseId, expenseDBManager.getParticipantList(expenseId));
            } else if (null == masterObject.getExpenseParticipant().get(expenseId)) {
                masterObject.getExpenseParticipant().put(expenseId, expenseDBManager.getParticipantList(expenseId));
            }
            if (null == masterObject.getExpenseRecords()) {
                masterObject.setExpenseRecords(new HashMap<Long, List<DailyRecords>>());
                masterObject.getExpenseRecords().put(expenseId, expenseDBManager.getRecordHistory(expenseId));
            } else if (null == masterObject.getExpenseRecords().get(expenseId)) {
                masterObject.getExpenseRecords().put(expenseId, expenseDBManager.getRecordHistory(expenseId));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            buildTable();
            expenseHelper.hideProgressBar(progressDialog);
        }
    }
}
