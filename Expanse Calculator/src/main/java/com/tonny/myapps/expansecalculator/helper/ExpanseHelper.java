package com.tonny.myapps.expansecalculator.helper;

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
    *Static 'instance' method
    */
    public static ExpanseHelper getInstance() {
        return expanseHelper;
    }

    public boolean isEmptyEditText(EditText editText) {
        return null != editText && editText.getText().toString().isEmpty();
    }
}
