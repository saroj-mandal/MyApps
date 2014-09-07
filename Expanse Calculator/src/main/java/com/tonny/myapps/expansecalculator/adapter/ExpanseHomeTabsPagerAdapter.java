package com.tonny.myapps.expansecalculator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tonny.myapps.expansecalculator.ExpanseListFragment;
import com.tonny.myapps.expansecalculator.PersonsFragment;

/**
 * Created by Tonny on 07-09-2014.
 */
public class ExpanseHomeTabsPagerAdapter extends FragmentPagerAdapter {
    public ExpanseHomeTabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Expanse list fragment activity
                return new ExpanseListFragment();
            case 1:
                // Add person to Expanse fragment activity
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
