package com.tonny.myapps.expansecalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tonny.myapps.expansecalculator.beans.Expanse;
import com.tonny.myapps.expansecalculator.utills.ExpanseDBManager;

/**
 * Created by Tonny on 02-09-2014.
 */
public class CreateExpanseActivity extends Activity {
    EditText expanseName;
    EditText expanseDesc;
    Button submitExpanse;
    SharedPreferences.Editor shPrefEditor;
    ExpanseDBManager expanseDBManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expanse);
        initializeFields();
        sharedPreferences = getBaseContext().getSharedPreferences("isFirstTimeAppLaunch", MODE_PRIVATE);
        expanseDBManager = new ExpanseDBManager(getApplicationContext());
    }

    private void initializeFields() {
        expanseName = (EditText) findViewById(R.id.etExpanseName);
        expanseDesc = (EditText) findViewById(R.id.etExpanseDesc);
        submitExpanse = (Button) findViewById(R.id.btSubmitExpanse);
    }

    public void createExpanse(View view) {
        switch (view.getId()) {
            case R.id.btSubmitExpanse:
                Expanse expanse = new Expanse();
                expanse.setName(expanseName.getText().toString());
                expanse.setDescription(expanseDesc.getText().toString());
                expanseDBManager.createExpanse(expanse);
                shPrefEditor = sharedPreferences.edit();
                shPrefEditor.putBoolean("isFirstTimeAppLaunch", false);
                shPrefEditor.commit();
                break;
            default:
                break;
        }
    }
}
