package com.tonny.myapps.expansecalculator.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.beans.Profile;
import com.tonny.myapps.expansecalculator.helper.ExpanseHelper;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

/**
 * Created by Tonny on 07-09-2014.
 */
public class AddPersonToExpanseFragment extends Fragment {
    EditText firstName;
    EditText surname;
    EditText emailId;
    Button addPersonToExpanse;
    ExpanseHelper expanseHelper = ExpanseHelper.getInstance();
    ExpenseDBManager expenseDBManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View addPersonView = inflater.inflate(R.layout.add_person_to_expanse_fragment_layout, container, false);
        initializeFields(addPersonView);
        Intent viewHistoryIntent = getActivity().getIntent();
        Bundle expanseDetailsBundle = viewHistoryIntent.getExtras();
        final long expenseId = expanseDetailsBundle.getLong("expenseId");
        addPersonToExpanse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProfile(expenseId);
            }
        });
        expenseDBManager = new ExpenseDBManager(getActivity().getApplicationContext());
        return addPersonView;
    }


    /**
     * Initializing fields
     */
    private void initializeFields(View addPersonView) {
        firstName = (EditText) addPersonView.findViewById(R.id.etFirstName);
        surname = (EditText) addPersonView.findViewById(R.id.etSurname);
        emailId = (EditText) addPersonView.findViewById(R.id.etEmailId);
        addPersonToExpanse = (Button) addPersonView.findViewById(R.id.btAddPerson);
    }

    private void createProfile(long expenseId) {
        if (!expanseHelper.isEmptyEditText(firstName)) {
            String fName = expanseHelper.getEditTextValue(firstName);
            String sName = expanseHelper.getEditTextValue(surname);
            String email = expanseHelper.getEditTextValue(emailId);
            Profile newMember = new Profile(fName, sName, email);
            long profileId = expenseDBManager.createNewProfile(newMember);
            expenseDBManager.createUserToExpenseRelation(expenseId, profileId);
        } else {
            String title = "Error :(";
            String message = "Name can't be empty";
            String buttonLabel = "OK";
            expanseHelper.showAlert(getActivity(), title, message, buttonLabel);
        }
    }

}
