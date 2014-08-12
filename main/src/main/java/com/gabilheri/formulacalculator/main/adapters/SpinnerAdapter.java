package com.gabilheri.formulacalculator.main.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.utils.Utils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/11/14.
 */
public class SpinnerAdapter<T> extends ArrayAdapter<T> {

    private Context context;
    private Theme currentTheme;

    public SpinnerAdapter(Context ctx, T [] objects) {
        super(ctx, android.R.layout.simple_spinner_item, objects);
        this.context = ctx;
        currentTheme = Utils.getCurrentTheme(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView v = (TextView) super.getView(position, convertView, parent);
        v.setTextColor(currentTheme.getDisplayTextColor());

        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View view = super.getView(position, convertView, parent);

        //we know that simple_spinner_item has android.R.id.text1 TextView:
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        text.setBackgroundColor(currentTheme.getDisplayColor());
        text.setTextColor(currentTheme.getDisplayTextColor());
        text.setPadding(25, 20, 25, 20);

        return view;
    }


}
