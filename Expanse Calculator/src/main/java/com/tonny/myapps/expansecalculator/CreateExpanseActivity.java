package com.tonny.myapps.expansecalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tonny.myapps.expansecalculator.beans.Expense;
import com.tonny.myapps.expansecalculator.helper.ExpanseHelper;
import com.tonny.myapps.expansecalculator.utills.ExpenseDBManager;

/**
 * Created by Tonny on 02-09-2014.
 */
public class CreateExpanseActivity extends Activity {
    EditText expanseName;
    EditText expanseDesc;
    Button submitExpanse;
    SharedPreferences.Editor shPrefEditor;
    ExpenseDBManager expenseDBManager;
    SharedPreferences sharedPreferences;
    ExpanseHelper expanseHelper = ExpanseHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expanse);
        initializeFields();
        sharedPreferences = getBaseContext().getSharedPreferences("isFirstTimeAppLaunch", MODE_PRIVATE);
        expenseDBManager = new ExpenseDBManager(getApplicationContext());
    }

    private void initializeFields() {
        expanseName = (EditText) findViewById(R.id.etExpanseName);
        expanseDesc = (EditText) findViewById(R.id.etExpanseDesc);
        submitExpanse = (Button) findViewById(R.id.btSubmitExpanse);
    }

    public void createExpanse(View view) {
        switch (view.getId()) {
            case R.id.btSubmitExpanse:
                createNewExpanse();
                break;
            default:
                break;
        }
    }

    private void createNewExpanse() {
        Expense expense = new Expense();
        if (!expanseHelper.isEmptyEditText(expanseName)) {
            expense.setName(expanseName.getText().toString());
            expense.setDescription(expanseDesc.getText().toString());
            expenseDBManager.createExpense(expense);
            shPrefEditor = sharedPreferences.edit();
            shPrefEditor.putBoolean("isFirstTimeAppLaunch", false);
            shPrefEditor.commit();
            finish();
            Intent openExpanseHomeActivity = new Intent(this, ExpanseHomeActivity.class);
            startActivity(openExpanseHomeActivity);
        } else {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error :(");
            alertDialog.setMessage("Name can't be empty");
            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                    return;
                }
            });
            alertDialog.show();
        }
    }
}
