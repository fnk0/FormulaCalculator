package com.gabilheri.formulacalculator.main.xmlElements;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class DefaultButton extends Button implements View.OnTouchListener{

    private int textColor, backgroundColor, highlightColor;
    private Context mContext;
    private MainActivity mActivity;

    /**
     *
     * @param context
     * @param attrs
     */
    public DefaultButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.setBackgroundColor(mContext.getResources().getColor(R.color.def_button));
        if(!this.isInEditMode()) {
            this.setOnTouchListener(this);
            this.mActivity = (MainActivity) context;
        }
    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            this.setBackgroundColor(mContext.getResources().getColor(R.color.def_button_pressed));
            mActivity.handleKeypad(this);
        } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
            this.setBackgroundColor(mContext.getResources().getColor(R.color.def_button));
        }
        return true;
    }
}
