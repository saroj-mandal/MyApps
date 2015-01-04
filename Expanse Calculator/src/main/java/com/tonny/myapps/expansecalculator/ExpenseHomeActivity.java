package com.tonny.myapps.expansecalculator;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tonny.myapps.expansecalculator.adapter.ExpenseHomeTabsPagerAdapter;

/**
 * Created by Tonny on 9/4/2014.
 */
public class ExpenseHomeActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager expenseHomeViewPager;
    private ActionBar expenseHomeActionBar;
    private ExpenseHomeTabsPagerAdapter expenseHomeTabsPagerAdapter;
    // Tab titles
    private String[] tabs = {"Expanses", "Persons"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanse_home);

        // Initialization
        expenseHomeViewPager = (ViewPager) findViewById(R.id.vpExpanseHomeTabPager);
        expenseHomeTabsPagerAdapter = new ExpenseHomeTabsPagerAdapter(getSupportFragmentManager());
        expenseHomeActionBar = getActionBar();
        expenseHomeViewPager.setAdapter(expenseHomeTabsPagerAdapter);
        expenseHomeActionBar.setHomeButtonEnabled(false);
        expenseHomeActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tabName : tabs) {
            expenseHomeActionBar.addTab(expenseHomeActionBar.newTab().setText(tabName).setTabListener(this));
        }
        //on swiping the viewpager make respective tab selected
        expenseHomeViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // on changing the page make respected tab selected
                expenseHomeActionBar.setSelectedNavigationItem(position);
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

    public void clickHandler(View view) {
        switch (view.getId()) {
            case R.id.btCreateExpanse:
                Intent createExpanseIntent = new Intent(this, CreateExpenseActivity.class);
                startActivity(createExpanseIntent);
                break;
            case R.id.btRefreshExpanse:

                break;
        }
    }
}
