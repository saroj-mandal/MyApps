package com.tonny.myapps.expansecalculator.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tonny.myapps.expansecalculator.R;
import com.tonny.myapps.expansecalculator.beans.Profile;

import java.util.List;

/**
 * Created by Tonny on 10/28/2014.
 */
public class UserListAdapter extends ArrayAdapter<Profile> {
    int resource;

    public UserListAdapter(Context context, int resource, List<Profile> profiles) {
        super(context, resource, profiles);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Profile profile = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder = new ViewHolder(); // view lookup cache stored in tag
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            // Lookup view for data population
            viewHolder.userName = (TextView) convertView.findViewById(R.id.tvViewUserName);
            viewHolder.emailId = (TextView) convertView.findViewById(R.id.tvViewEmailId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        String userName = profile.getFirstName();
        if (null != profile.getLastName()) {
            userName = userName + " " + profile.getLastName();
        }
        viewHolder.userName.setText(userName);
        if (null != profile.getEmailId()) {
            viewHolder.emailId.setText(profile.getEmailId());
        }
        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView userName;
        TextView emailId;
    }
}
