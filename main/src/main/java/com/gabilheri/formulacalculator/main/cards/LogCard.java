package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.ResultLog;
import com.gabilheri.formulacalculator.main.utils.Utils;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/12/14
 */
public class LogCard extends Card implements Card.OnSwipeListener, Card.OnCardClickListener, Card.OnUndoSwipeListListener {

    private String mInput;
    private String mResult;
    private long logID;
    private DatabaseHelper dbHelper;
    private Context mContext;
    private ResultLog cardLog;
    public static final String LOG_TAG = "LogCard";


    public LogCard(Context context) {
        super(context, R.layout.log_card);
        this.mContext = context;
        dbHelper = new DatabaseHelper(context);
        setSwipeable(true);
        setOnSwipeListener(this);
        setOnClickListener(this);
        setOnUndoSwipeListListener(this);
    }

    public LogCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    public String getInput() {
        return mInput;
    }

    public LogCard setInput(String mInput) {
        this.mInput = mInput;
        return this;
    }

    public String setResult() {
        return mResult;
    }

    public LogCard setResult(String mResult) {
        this.mResult = mResult;
        return this;
    }

    public long getLogID() {
        return logID;
    }

    public LogCard setLogID(long logID) {
        this.logID = logID;
        return this;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        TextView inpuText = (TextView) view.findViewById(R.id.inputLog);
        TextView resultLog = (TextView) view.findViewById(R.id.resultLog);

        inpuText.setText(Html.fromHtml(mInput));
        resultLog.setText(Html.fromHtml(mResult));
    }

    @Override
    public void onSwipe(Card card) {
        Log.i(LOG_TAG, "Deleting card with ID = " + logID);
        dbHelper.deleteResultLog(logID);
    }

    @Override
    public void onClick(Card card, View view) {
        Bundle resultBundle = new Bundle();
        resultBundle.putString("logResult", Utils.getCurrentCalcInput(mContext) + Double.parseDouble(mResult));

        MainActivity mActivity = (MainActivity) mContext;
        mActivity.displayView(MainActivity.CALCULATOR_FRAG, resultBundle);
    }

    @Override
    public void onUndoSwipe(Card card) {
        dbHelper.createResultLog(cardLog);
    }

    public ResultLog getCardLog() {
        return cardLog;
    }

    public void setCardLog(ResultLog cardLog) {
        this.cardLog = cardLog;
    }
}
