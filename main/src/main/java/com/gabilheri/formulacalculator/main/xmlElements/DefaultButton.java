package com.gabilheri.formulacalculator.main.xmlElements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/15/14
 */
public class DefaultButton extends Button implements View.OnClickListener {

    private int textColor, backgroundColor, highlightColor;
    private Context mContext;
    private MainActivity mActivity;
    private StateListDrawable statesList;

    /**
     *
     * @param context
     * @param attrs
     */
    public DefaultButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        statesList = new StateListDrawable();

        textColor = context.getResources().getColor(R.color.list_background);
        backgroundColor = context.getResources().getColor(R.color.def_button);
        highlightColor = context.getResources().getColor(R.color.def_button_pressed);

        statesList.addState(new int[] {android.R.attr.state_pressed}, new ColorDrawable(highlightColor));
        statesList.addState(new int[] {android.R.attr.state_selected}, new ColorDrawable(context.getResources().getColor(R.color.light_orange)));
        statesList.addState(new int[] {}, new ColorDrawable(backgroundColor));

        this.mContext = context;
        this.setTextColor(textColor);
        this.setBackground(statesList);

        if(!this.isInEditMode()) {
            this.setOnClickListener(this);
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

    /**
     * This methods returns a integer representation of a Color object
     *
     * @return
     *      the current background of this button
     */
    public int getCustomBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the custom background of this object
     * @param backgroundColor
     *          a integer representing a Color object
     * @return
     *          this object for chainable purposes
     */
    public DefaultButton setCustomBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * getter for the highlight color of this button
     * the highlight color is the one that will be represented whenever the button is pressed
     *
     * @return
     *       a integer representing a Color object
     *
     *
     */
    public int getCustomHighlightColor() {
        return highlightColor;
    }

    /**
     *
     * @param highlightColor
     * @return
     */
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

    @Override
    public void onClick(View v) {
        mActivity.handleKeypad(this);
    }
}
