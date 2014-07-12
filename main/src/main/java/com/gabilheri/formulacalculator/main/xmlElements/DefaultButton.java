package com.gabilheri.formulacalculator.main.xmlElements;

import android.content.Context;
import android.graphics.Canvas;
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

    private int textColor, backgroundColor, highlightColor, selectedColor;
    private Context mContext;
    private MainActivity mActivity;

    /**
     *
     * @param context
     *          The context in which this button is being called
     * @param attrs
     *          Used internally by Android
     */
    public DefaultButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        textColor = context.getResources().getColor(R.color.list_background);
        backgroundColor = context.getResources().getColor(R.color.def_button);
        highlightColor = context.getResources().getColor(R.color.def_button_pressed);
        selectedColor = context.getResources().getColor(R.color.light_orange);

        this.mContext = context;
        this.setTextColor(textColor);
        this.setBackgroundColor(backgroundColor);
        this.setBackground(new CustomStateList(context, backgroundColor, highlightColor, selectedColor));

        /**
         * This is necessary so the Layout editor can render this button in real time.
         */
        if(!this.isInEditMode()) {
            this.setOnClickListener(this);
            this.mActivity = (MainActivity) context;
            this.setTextSize(getResources().getDimension(R.dimen.button_text_size));
        }


    }

    /**
     * Getter for the custom text color.
     *
     * @return
     *         The integer representation of this Color object
     */
    public int getCustomTextColor() {
        return textColor;
    }

    /**
     * Setter for the text color of this button
     * @param textColor
     *          The integer representation of a hex value or rgb color.
     * @return
     *          This object for easy to chain builder
     */
    public DefaultButton setCustomTextColor(int textColor) {
        this.textColor = textColor;
        this.setTextColor(textColor);
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

        this.setBackground(new CustomStateList(mContext, backgroundColor, highlightColor, selectedColor));

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
     *          Integer representation of a hex value or rgb color
     * @return
     *          This object for easy to chain build
     */
    public DefaultButton setCustomHighlightColor(int highlightColor) {
        this.highlightColor = highlightColor;

        this.setBackground(new CustomStateList(mContext, backgroundColor, highlightColor, selectedColor));
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
