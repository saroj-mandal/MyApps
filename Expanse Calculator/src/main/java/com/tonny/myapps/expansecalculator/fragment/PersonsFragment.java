package com.tonny.myapps.expansecalculator.fragment;

import android.app.ProgressDialog;
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
import com.tonny.myapps.expansecalculator.adapter.UserListAdapter;
import com.tonny.myapps.expansecalculator.helper.ExpenseHelper;
import com.tonny.myapps.expansecalculator.helper.MasterObject;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

/**
 * Created by Tonny on 07-09-2014.
 */
public class PersonsFragment extends Fragment {
    ExpenseDBManager expenseDBManager;
    ListView allUserListView;
    MasterObject masterObject = MasterObject.getInstance();
    ExpenseHelper expenseHelper = ExpenseHelper.getInstance();
    View memberListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        memberListView = inflater.inflate(R.layout.persons_fragment_layout, container, false);
        FetchMemberListAsyncTask fetchMemberListAsyncTask = new FetchMemberListAsyncTask();
        fetchMemberListAsyncTask.execute();
        allUserListView = (ListView) memberListView.findViewById(R.id.lvUser);
        return memberListView;

    }

    private class FetchMemberListAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = expenseHelper.showProgressBar(getActivity());
        }

        @Override
        protected Void doInBackground(Void... params) {
            expenseDBManager = new ExpenseDBManager(getActivity());
            if (null == masterObject.getMemberList()) {
                masterObject.setMemberList(expenseDBManager.getAllUserList());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (masterObject.getMemberList().size() > 0) {
                TextView personHeader = (TextView) memberListView.findViewById(R.id.tvPersonHeader);
                if (null != personHeader) {
                    ((RelativeLayout) personHeader.getParent()).removeView(personHeader);
                }
            }
            UserListAdapter expanseListAdapter = new UserListAdapter(memberListView.getContext(), R.layout.activity_user_item_fg_layout, masterObject.getMemberList());
            allUserListView.setAdapter(expanseListAdapter);
            allUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                }
            });
            expenseHelper.hideProgressBar(progressDialog);
        }
    }
}
