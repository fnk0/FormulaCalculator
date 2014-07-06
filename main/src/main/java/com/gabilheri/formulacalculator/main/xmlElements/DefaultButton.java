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
        textColor = context.getResources().getColor(R.color.list_background);
        backgroundColor = context.getResources().getColor(R.color.def_button);
        highlightColor = context.getResources().getColor(R.color.def_button_pressed);
        this.mContext = context;
        this.setTextColor(textColor);
        this.setBackgroundColor(backgroundColor);

        if(!this.isInEditMode()) {
            this.setOnTouchListener(this);
            this.mActivity = (MainActivity) context;
            //this.setTextSize(getResources().getDimension(R.dimen.button_text_size));
        }
    }

    public int getCustomTextColor() {
        return textColor;
    }

    public DefaultButton setCustomTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getCustomBackgroundColor() {
        return backgroundColor;
    }

    public DefaultButton setCustomBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setBackgroundColor(backgroundColor);
        return this;
    }

    public int getCustomHighlightColor() {
        return highlightColor;
    }

    public DefaultButton setCustomHighlightColor(int highlightColor) {
        this.highlightColor = highlightColor;
        return this;
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
            this.setBackgroundColor(backgroundColor);
            mActivity.handleKeypad(this);
        } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
            this.setBackgroundColor(highlightColor);
        } else if(event.getAction() == MotionEvent.ACTION_CANCEL) {
            this.setBackgroundColor(backgroundColor);
        }
        return true;
    }
}
