package com.tonny.myapps.expansecalculator;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tonny.myapps.expansecalculator.adapter.ViewExpanseTabsPagerAdapter;

/**
 * Created by Tonny on 9/4/2014.
 */
public class ViewExpanseHistory extends FragmentActivity implements ActionBar.TabListener {
    private ViewPager viewRecordsViewPager;
    private ActionBar viewRecordsActionBar;
    private ViewExpanseTabsPagerAdapter viewExpanseTabsPagerAdapter;

    // Tab titles
    private String[] tabs = {"Records", "Entry", "Add Person"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        // Initialization
        viewRecordsViewPager = (ViewPager) findViewById(R.id.vpViewRecordsTabPager);
        viewExpanseTabsPagerAdapter = new ViewExpanseTabsPagerAdapter(getSupportFragmentManager());
        viewRecordsActionBar = getActionBar();
        viewRecordsViewPager.setAdapter(viewExpanseTabsPagerAdapter);
        viewRecordsActionBar.setHomeButtonEnabled(false);
        viewRecordsActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tabName : tabs) {
            viewRecordsActionBar.addTab(viewRecordsActionBar.newTab().setText(tabName).setTabListener(this));
        }
        //on swiping the viewpager make respective tab selected
        viewRecordsViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                // on changing the page make respected tab selected
                viewRecordsActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

}

