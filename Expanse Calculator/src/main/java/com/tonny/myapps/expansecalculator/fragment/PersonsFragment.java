package com.tonny.myapps.expansecalculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.adapter.UserListAdapter;
import com.tonny.myapps.expansecalculator.beans.Profile;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

import java.util.List;

/**
 * Created by Tonny on 07-09-2014.
 */
public class PersonsFragment extends Fragment {
    ExpenseDBManager expenseDBManager;
    List<Profile> userList;
    ListView allUserListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View memberListView = inflater.inflate(R.layout.persons_fragment_layout, container, false);
        expenseDBManager = new ExpenseDBManager(getActivity());
        userList = expenseDBManager.getAllUserList();
        allUserListView = (ListView) memberListView.findViewById(R.id.lvUser);
        UserListAdapter expanseListAdapter = new UserListAdapter(memberListView.getContext(), R.layout.activity_user_item_fg_layout, userList);
        allUserListView.setAdapter(expanseListAdapter);
        allUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            }
        });
        return memberListView;

    }
}
