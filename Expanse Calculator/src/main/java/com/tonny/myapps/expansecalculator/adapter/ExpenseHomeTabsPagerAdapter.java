package com.tonny.myapps.expansecalculator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tonny.myapps.expansecalculator.fragment.ExpenseListFragment;
import com.tonny.myapps.expansecalculator.fragment.PersonsFragment;

/**
 * Created by Tonny on 07-09-2014.
 */
public class ExpenseHomeTabsPagerAdapter extends FragmentPagerAdapter {
    public ExpenseHomeTabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Expense list fragment activity
                return new ExpenseListFragment();
            case 1:
                // Add person to Expense fragment activity
                return new PersonsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
}
