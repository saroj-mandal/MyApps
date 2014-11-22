package com.tonny.myapps.expansecalculator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.adapter.ExpanseListAdapter;
import com.tonny.myapps.expansecalculator.adapter.ParticipantListAdapter;
import com.tonny.myapps.expansecalculator.adapter.UserListAdapter;
import com.tonny.myapps.expansecalculator.beans.DailyRecords;
import com.tonny.myapps.expansecalculator.beans.Expense;
import com.tonny.myapps.expansecalculator.beans.Profile;
import com.tonny.myapps.expansecalculator.helper.ExpanseHelper;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tonny on 07-09-2014.
 */
public class AddTodaysExpanseFragment extends Fragment {
    ListView participant;
    ListView contributor;
    Button submitTodaysSpending;
    EditText amount;
    ExpenseDBManager expenseDBManager;
    List<Profile> participantList;
    ExpanseHelper expanseHelper = ExpanseHelper.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View addTodaysExpanseView = inflater.inflate(R.layout.add_todays_expanse_fragment_layout, container, false);
        Intent viewHistoryIntent = getActivity().getIntent();
        Bundle expanseDetailsBundle = viewHistoryIntent.getExtras();
        final long expenseId = expanseDetailsBundle.getLong("expenseId");
        expenseDBManager = new ExpenseDBManager(getActivity());
        participant = (ListView) addTodaysExpanseView.findViewById(R.id.lvParticipant);
        contributor = (ListView) addTodaysExpanseView.findViewById(R.id.lvContributor);
        submitTodaysSpending = (Button) addTodaysExpanseView.findViewById(R.id.btSubmitTodaysSpending);
        amount = (EditText) addTodaysExpanseView.findViewById(R.id.etAmount);
        participantList = expenseDBManager.getParticipantList(expenseId);
        final ParticipantListAdapter participantListAdapter = new ParticipantListAdapter(addTodaysExpanseView.getContext(), R.layout.activity_participant_item_fg_layout, participantList);
        final ParticipantListAdapter contributorListAdapter = new ParticipantListAdapter(addTodaysExpanseView.getContext(), R.layout.activity_participant_item_fg_layout, participantList);
        participant.setAdapter(participantListAdapter);
        contributor.setAdapter(contributorListAdapter);
        submitTodaysSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!expanseHelper.isEmptyEditText(amount)) {
                    if (contributorListAdapter.getCount() > 0 && participantListAdapter.getCount() > 0) {
                        DailyRecords dailyRecords = new DailyRecords();
                        List<Long> participantIdList = new ArrayList<Long>();
                        for (int count = 0; count < contributorListAdapter.getCount(); count++) {
                            Profile profile = contributorListAdapter.getItem(count);
                            dailyRecords.setAmount(Integer.parseInt(expanseHelper.getEditTextValue(amount)));
                            dailyRecords.setPayerId(profile.getId());
                            dailyRecords.setExpanseId(expenseId);
                        }
                        for (int count = 0; count < participantListAdapter.getCount(); count++) {
                            Profile profile = participantListAdapter.getItem(count);
                            participantIdList.add(profile.getId());
                        }
                        dailyRecords.setParticipantIdList(participantIdList);
                        if (expenseDBManager.createDailyRecords(dailyRecords) > 0) {
                            String title = "Success";
                            String message = "Amount Rs. " + expanseHelper.getEditTextValue(amount)
                                    + "added to :" + expenseId;
                            String buttonLabel = "Dismiss";
                            expanseHelper.showAlert(getActivity(), title, message, buttonLabel);
                        }
                    } else {
                        String title = "Error";
                        String message = "You have to select at least one participant and contributor";
                        String buttonLabel = "Dismiss";
                        expanseHelper.showAlert(getActivity(), title, message, buttonLabel);
                    }
                } else {
                    String title = "Error";
                    String message = "Amount can't be empty";
                    String buttonLabel = "Dismiss";
                    expanseHelper.showAlert(getActivity(), title, message, buttonLabel);
                }
            }
        });
        return addTodaysExpanseView;
    }
}
