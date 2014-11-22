package com.tonny.myapps.expansecalculator.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Created by smand6 on 9/4/2014.
 */
public class ExpanseHelper {
    private static ExpanseHelper expanseHelper = new ExpanseHelper();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private ExpanseHelper() {
    }

    /*
     * Static 'instance' method
     */
    public static ExpanseHelper getInstance() {
        return expanseHelper;
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
        if (!expanseHelper.isEmptyEditText(editText)) {
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
}
