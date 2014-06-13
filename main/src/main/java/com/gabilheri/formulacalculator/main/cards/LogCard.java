package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/12/14
 */
public class LogCard extends Card {

    private String mInput;
    private String mResult;

    public LogCard(Context context) {
        super(context, R.layout.log_card);
    }

    public LogCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    public String getmInput() {
        return mInput;
    }

    public LogCard setmInput(String mInput) {
        this.mInput = mInput;
        return this;
    }

    public String getmResult() {
        return mResult;
    }

    public LogCard setmResult(String mResult) {
        this.mResult = mResult;
        return this;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        TextView inpuText = (TextView) view.findViewById(R.id.inputLog);
        TextView resultLog = (TextView) view.findViewById(R.id.resultLog);

        inpuText.setText(Html.fromHtml(mInput));
        resultLog.setText(Html.fromHtml(mResult));


    }
}
