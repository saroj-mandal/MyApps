package com.tonny.myapps.expansecalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.beans.Profile;
import com.tonny.myapps.expansecalculator.utills.ExpanseDBManager;


public class ExpanseCalculatorActivity extends Activity {
    EditText expanseName;
    EditText expanseDesc;
    Button addExpanse;
    TextView result;
    Editor shPrefEditor;
    ExpanseDBManager expanseDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expanseDBManager = new ExpanseDBManager(getApplicationContext());
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("isFirstTimeAppLaunch", MODE_PRIVATE);
        boolean isFirstTimeAppLaunch = sharedPreferences.getBoolean("isFirstTimeAppLaunch", false);
        if (!isFirstTimeAppLaunch) {
            setContentView(R.layout.activity_welcome_expanse_calculator);
            shPrefEditor = sharedPreferences.edit();
            shPrefEditor.putBoolean("isFirstTimeAppLaunch", false);
            shPrefEditor.commit();
            String emailId = "saroj.mandal1989@gmail.com";
            Profile profile = expanseDBManager.getProfile(emailId);
            result = (TextView) findViewById(R.id.tvResult);
            result.setText(profile.getFirstName() + " " + profile.getLastName());
        } else {
            setContentView(R.layout.activity_expanse_calculator);
            Profile profile = new Profile(100001, "Saroj", "Mandal", "saroj.mandal1989@gmail.com");
            expanseDBManager.createNewProfile(profile);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expanse_calculator, menu);
        return true;
    }

    @
            Override
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
