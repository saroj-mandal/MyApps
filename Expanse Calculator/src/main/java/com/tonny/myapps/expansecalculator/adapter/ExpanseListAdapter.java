package com.tonny.myapps.expansecalculator.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.beans.Expense;

import java.util.List;

/**
 * Created by Tonny on 9/18/2014.
 */
public class ExpanseListAdapter extends ArrayAdapter<Expense> {
    int resource;

    public ExpanseListAdapter(Context context, int resource, List<Expense> expenses) {
        super(context, resource, expenses);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Expense expense = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder = new ViewHolder(); // view lookup cache stored in tag
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            // Lookup view for data population
            viewHolder.expanseName = (TextView) convertView.findViewById(R.id.tvViewExpanseName);
            viewHolder.expanseDesc = (TextView) convertView.findViewById(R.id.tvViewExpanseDesc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.expanseName.setText(expense.getName());
        viewHolder.expanseDesc.setText(expense.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView expanseName;
        TextView expanseDesc;
    }
}
