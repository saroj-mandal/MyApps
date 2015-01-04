package com.tonny.myapps.expansecalculator.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.tonny.myapps.expansecalculator.helper.ExpenseHelper;
import com.tonny.myapps.expansecalculator.helper.MasterObject;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

/**
 * Created by Tonny on 07-09-2014.
 */
public class AddPersonToExpenseFragment extends Fragment {
    EditText firstName;
    EditText surname;
    EditText emailId;
    Button addPersonToExpanse;
    ExpenseHelper expenseHelper = ExpenseHelper.getInstance();
    MasterObject masterObject = MasterObject.getInstance();
    ExpenseDBManager expenseDBManager;
    long expenseId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View addPersonView = inflater.inflate(R.layout.add_person_to_expanse_fragment_layout, container, false);
        initializeFields(addPersonView);
        Intent viewHistoryIntent = getActivity().getIntent();
        Bundle expanseDetailsBundle = viewHistoryIntent.getExtras();
        expenseId = expanseDetailsBundle.getLong("expenseId");
        addPersonToExpanse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPersonAsyncTask addPersonAsyncTask = new AddPersonAsyncTask();
                addPersonAsyncTask.execute();
            }
        });
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
        if (!expenseHelper.isEmptyEditText(firstName)) {
            String fName = expenseHelper.getEditTextValue(firstName);
            String sName = expenseHelper.getEditTextValue(surname);
            String email = expenseHelper.getEditTextValue(emailId);
            Profile newMember = new Profile(fName, sName, email);
            long profileId = expenseDBManager.createNewProfile(newMember);
            expenseDBManager.createUserToExpenseRelation(expenseId, profileId);
            if (null != masterObject.getMemberList()) {
                masterObject.getMemberList().add(newMember);
            }
        } else {
            String title = "Error :(";
            String message = "Name can't be empty";
            String buttonLabel = "OK";
            expenseHelper.showAlert(getActivity(), title, message, buttonLabel);
        }
    }

    private class AddPersonAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = expenseHelper.showProgressBar(getActivity());
        }

        @Override
        protected Void doInBackground(Void... params) {
            expenseDBManager = new ExpenseDBManager(getActivity());
            createProfile(expenseId);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            expenseHelper.hideProgressBar(progressDialog);
        }
    }

}
