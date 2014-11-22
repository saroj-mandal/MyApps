package com.tonny.myapps.expansecalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;


public class ExpanseCalculatorActivity extends Activity {
    TextView tvErrorMessage;
    ExpenseDBManager expenseDBManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("isFirstTimeAppLaunch", MODE_PRIVATE);
        boolean isFirstTimeAppLaunch = sharedPreferences.getBoolean("isFirstTimeAppLaunch", true);
        if (isFirstTimeAppLaunch) {
            setContentView(R.layout.activity_welcome_expanse_calculator);
        } else {
            Intent openExpanseHomeActivity = new Intent(this, ExpanseHomeActivity.class);
            startActivity(openExpanseHomeActivity);
            finish();
        }
    }

    public void clickHandler(View view) {
        switch (view.getId()) {
            case R.id.btCreateExpanse:
                Intent createExpanseIntent = new Intent(this, CreateExpanseActivity.class);
                startActivity(createExpanseIntent);
                break;
            default:
                tvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);
                tvErrorMessage.setText("Sorry, Unable to open create expanse Page ");
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expanse_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
