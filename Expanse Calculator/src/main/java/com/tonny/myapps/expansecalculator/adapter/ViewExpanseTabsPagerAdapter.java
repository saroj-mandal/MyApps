package com.tonny.myapps.expansecalculator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tonny.myapps.expansecalculator.fragment.AddPersonToExpanseFragment;
import com.tonny.myapps.expansecalculator.fragment.AddTodaysExpanseFragment;
import com.tonny.myapps.expansecalculator.fragment.RecordsHistoryFragment;

/**
 * Created by Tonny on 07-09-2014.
 */
public class ViewExpanseTabsPagerAdapter extends FragmentPagerAdapter {
    public ViewExpanseTabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                // View expanse history fragment activity
                return new RecordsHistoryFragment();
            case 1:
                // Add today's expanses fragment activity
                return new AddTodaysExpanseFragment();
            case 2:
                // Add person to expanse fragment activity
                return new AddPersonToExpanseFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}
