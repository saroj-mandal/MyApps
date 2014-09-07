package com.tonny.myapps.expansecalculator;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tonny.myapps.expansecalculator.adapter.ExpanseHomeTabsPagerAdapter;

/**
 * Created by smand6 on 9/4/2014.
 */
public class ExpanseHomeActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager expanseHomeViewPager;
    private ActionBar expanseHomeActionBar;
    private ExpanseHomeTabsPagerAdapter expanseHomeTabsPagerAdapter;
    // Tab titles
    private String[] tabs = {"Expanses", "Persons"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanse_home);

        // Initialization
        expanseHomeViewPager = (ViewPager) findViewById(R.id.vpExpanseHomeTabPager);
        expanseHomeTabsPagerAdapter = new ExpanseHomeTabsPagerAdapter(getSupportFragmentManager());
        expanseHomeActionBar = getActionBar();
        expanseHomeViewPager.setAdapter(expanseHomeTabsPagerAdapter);
        expanseHomeActionBar.setHomeButtonEnabled(false);
        expanseHomeActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tabName : tabs) {
            expanseHomeActionBar.addTab(expanseHomeActionBar.newTab().setText(tabName).setTabListener(this));
        }
        //on swiping the viewpager make respective tab selected
        expanseHomeViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // on changing the page make respected tab selected
                expanseHomeActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public void viewExpanse(View view) {
        Intent viewHistoryIntent = new Intent(this, ViewExpanseHistory.class);
        startActivity(viewHistoryIntent);
    }
}
