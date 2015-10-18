package com.csc394.gsp.gradschoolplanner;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableCourseAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> headers;
    private HashMap<String, List<String>> child_data;

    ExpandableCourseAdapter(Context contxt, List<String> h, HashMap<String, List<String>> c) {
        this.context = contxt;
        this.headers = h;
        this.child_data = c;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.child_data.get(this.headers.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastchild,
                             View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_item, parent, false);
        }

        TextView txtChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.child_data.get(this.headers.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_header, parent, false);
        }

        TextView header = (TextView) convertView.findViewById(R.id.lblListHeader);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
