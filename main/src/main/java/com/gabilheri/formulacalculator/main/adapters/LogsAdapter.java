package com.gabilheri.formulacalculator.main.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.ResultLog;

import java.util.List;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class LogsAdapter extends ArrayAdapter<ResultLog> {

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param objects
     *         The objects to represent in the ListView.
     */
    public LogsAdapter(Context context, List<ResultLog> objects) {
        super(context, R.layout.list_log, objects);

    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ResultLog resultLog = getItem(position);

        ViewHolder mViewHolder;

        if(convertView == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_log, parent, false);
            mViewHolder.inputView = (TextView) convertView.findViewById(R.id.logInput);
            mViewHolder.resultView = (TextView) convertView.findViewById(R.id.logResult);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            mViewHolder.inputView.setText(Html.fromHtml(resultLog.getInput()));
            mViewHolder.resultView.setText(Html.fromHtml(resultLog.getResult()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView inputView;
        TextView resultView;
    }
}
