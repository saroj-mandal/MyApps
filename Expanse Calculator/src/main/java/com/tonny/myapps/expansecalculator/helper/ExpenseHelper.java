package com.tonny.myapps.expansecalculator.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by smand6 on 9/4/2014.
 */
public class ExpenseHelper {
    private static ExpenseHelper expenseHelper = new ExpenseHelper();
    private static ProgressDialog progressDialog;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private ExpenseHelper() {
    }

    /*
     * Static 'instance' method
     */
    public static ExpenseHelper getInstance() {
        return expenseHelper;
    }

    /*
     * Identify whether a EditText field is empty or not.
     */
    public boolean isEmptyEditText(EditText editText) {
        return null != editText && editText.getText().toString().isEmpty();
    }

    /*
     * return EditText value.
     */
    public String getEditTextValue(EditText editText) {
        String editTextValue = null;
        if (!expenseHelper.isEmptyEditText(editText)) {
            editTextValue = editText.getText().toString();
        }
        return editTextValue;
    }

    public void showAlert(Activity activity, String title, String message, String buttonLabel) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, buttonLabel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                return;
            }
        });
        alertDialog.show();
    }

    public ProgressDialog showProgressBar(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    public void hideProgressBar(ProgressDialog currProgressDialog) {
        currProgressDialog.hide();
    }

    public TextView createTextView(Activity activity, String title, int color) {
        TextView textView = new TextView(activity);
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, 5, 0, 5);
        textView.setText(title);
        textView.setTextColor(color);
        return textView;
    }
}
