package com.gabilheri.formulacalculator.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.navDrawer.NavDrawerItem;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.ArrayList;

/**
 * Created by marcus on 5/6/14.
 * @author Marcus Gabilheri
 * @since May 2014
 * @version 1.0
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private View mConvertView;

    public NavDrawerListAdapter() {
    }

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<NavDrawerItem> getNavDrawerItems() {
        return navDrawerItems;
    }

    public void setNavDrawerItems(ArrayList<NavDrawerItem> navDrawerItems) {
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imageIcon = (ImageView) convertView.findViewById(R.id.navDrawerIcon);
        TextView title = (TextView) convertView.findViewById(R.id.navDrawerTitle);

        if(position == 0) {
            mConvertView = convertView;
            mConvertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelSize(R.dimen.action_bar_size) + Utils.getStatusBarHeight(context)));
            mConvertView.setBackground(navDrawerItems.get(position).getDrawable());
        } else {
            title.setText(navDrawerItems.get(position).getTitle());
            imageIcon.setImageResource(navDrawerItems.get(position).getIcon());

        }

        return convertView;
    }

    public View getmConvertView() {
        return mConvertView;
    }
}
