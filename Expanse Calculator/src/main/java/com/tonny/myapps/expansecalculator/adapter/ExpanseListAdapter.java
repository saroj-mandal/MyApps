package com.tonny.myapps.expansecalculator.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.beans.Expanse;

import java.util.List;

/**
 * Created by smand6 on 9/18/2014.
 */
public class ExpanseListAdapter extends ArrayAdapter<Expanse> {
    int resource;

    public ExpanseListAdapter(Context context, int resource, List<Expanse> expanses) {
        super(context, resource, expanses);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Expanse expanse = getItem(position);
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
        viewHolder.expanseName.setText(expanse.getName());
        viewHolder.expanseDesc.setText(expanse.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView expanseName;
        TextView expanseDesc;
    }
}
