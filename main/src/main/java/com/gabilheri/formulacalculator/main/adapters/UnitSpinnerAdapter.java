package com.gabilheri.formulacalculator.main.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.logic.unit.Unit;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/24/14.
 */
public class UnitSpinnerAdapter extends ArrayAdapter<Unit> {

    private Context context;
    private Theme currentTheme;
    private ArrayList<Unit> units;

    public UnitSpinnerAdapter(Context context, ArrayList<Unit> objects) {
        super(context, android.R.layout.simple_spinner_item, objects);
        units = objects;
        this.context = context;
        currentTheme = Utils.getCurrentTheme(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView =  (TextView) super.getView(position, convertView, parent);
        textView.setText(units.get(position).getName());
        textView.setTextColor(currentTheme.getDisplayTextColor());

        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view =  super.getDropDownView(position, convertView, parent);

        //we know that simple_spinner_item has android.R.id.text1 TextView:
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        text.setText(units.get(position).getName());
        text.setBackgroundColor(currentTheme.getDisplayColor());
        text.setTextColor(currentTheme.getDisplayTextColor());
        text.setHeight((int) context.getResources().getDimension(R.dimen.text_spinner_height));
        text.setGravity(Gravity.CENTER_VERTICAL);
        return view;
    }
}
