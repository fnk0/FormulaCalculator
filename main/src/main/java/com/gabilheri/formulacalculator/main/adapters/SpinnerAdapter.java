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

    public SpinnerAdapter(Context ctx, T [] objects) {
        super(ctx, android.R.layout.simple_spinner_item, objects);
        this.context = ctx;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View view = super.getView(position, convertView, parent);

        //we know that simple_spinner_item has android.R.id.text1 TextView:
        Theme currentTheme = Utils.getCurrentTheme(context);
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        text.setBackgroundColor(currentTheme.getDisplayColor());
        text.setTextColor(currentTheme.getDisplayTextColor());

        return view;

    }
}
