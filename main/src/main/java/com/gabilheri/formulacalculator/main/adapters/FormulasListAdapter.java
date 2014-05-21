package com.gabilheri.formulacalculator.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by marcus on 5/9/14.
 * @author Marcus Gabilheri
 * @since May 2014
 * @version 1.0
 */
public class FormulasListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> formulaTitles;
    private HashMap<String, List<String>> formulaChilds;

    public FormulasListAdapter() {
    }

    public FormulasListAdapter(Context context, List<String> formulaTitles, HashMap<String, List<String>> formulaChilds) {
        this.context = context;
        this.formulaTitles = formulaTitles;
        this.formulaChilds = formulaChilds;
    }

    @Override
    public int getGroupCount() {
        return formulaTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return formulaChilds.get(formulaTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return formulaTitles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return formulaChilds.get(formulaTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String formulaTitle = (String) getGroup(groupPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.listSubject);
        titleView.setText(formulaTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView formula = (TextView) convertView.findViewById(R.id.listItem);
        formula.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
