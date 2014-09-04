package com.tonny.myapps.expansecalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.tonny.myapps.expansecalculator.utills.ExpanseDBManager;

/**
 * Created by smand6 on 9/4/2014.
 */
public class ExpanseHomeActivity extends Activity {
    SharedPreferences.Editor shPrefEditor;
    ListView existingExpanses;
    ExpanseDBManager expanseDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expanseDBManager = new ExpanseDBManager(getApplicationContext());
        setContentView(R.layout.activity_expanse_home);
        String[] dbColumns = new String[]{"name", "description"};
        int[] viewIds = new int[]{R.id.tvViewExpanseName, R.id.tvViewExpanseDesc};
        existingExpanses = (ListView) findViewById(R.id.lvExistingExpanse);
        Cursor cursor = expanseDBManager.getExpanseCursor();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_expanse_item_fg_layout, cursor, dbColumns, viewIds);
        existingExpanses.setAdapter(simpleCursorAdapter);
    }

    public void viewExpanse(View view) {
        Intent viewHistoryIntent = new Intent(this, ViewExpanseHistory.class);
        startActivity(viewHistoryIntent);
    }
}
