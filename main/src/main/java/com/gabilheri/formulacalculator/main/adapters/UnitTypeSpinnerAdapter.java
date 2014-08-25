package com.gabilheri.formulacalculator.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.logic.unit.UnitSpinnerItem;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/11/14.
 */
public class UnitTypeSpinnerAdapter extends BaseAdapter {

    private Context context;
    private Theme currentTheme;
    private ArrayList<UnitSpinnerItem> spinnerItems;

    public UnitTypeSpinnerAdapter(Context ctx, ArrayList<UnitSpinnerItem> spinnerItems) {
        this.context = ctx;
        currentTheme = Utils.getCurrentTheme(context);
        this.spinnerItems = spinnerItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.unit_spinner, null);

        convertView.setBackgroundColor(currentTheme.getDisplayColor());

        TextView title = (TextView) convertView.findViewById(R.id.unitName);
        title.setTextColor(currentTheme.getDisplayTextColor());
        title.setText(spinnerItems.get(position).getTitle());

        ImageView icon = (ImageView) convertView.findViewById(R.id.unitIcon);
        icon.setImageResource(spinnerItems.get(position).getIcon());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.unit_spinner, null);

        convertView.setBackgroundColor(currentTheme.getDisplayColor());

        TextView text = (TextView) convertView.findViewById(R.id.unitName);
        text.setBackgroundColor(currentTheme.getDisplayColor());
        text.setTextColor(currentTheme.getDisplayTextColor());
        text.setText(spinnerItems.get(position).getTitle());

        ImageView icon = (ImageView) convertView.findViewById(R.id.unitIcon);
        icon.setImageResource(spinnerItems.get(position).getIcon());

        return convertView;
    }


    @Override
    public int getCount() {
        return spinnerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
