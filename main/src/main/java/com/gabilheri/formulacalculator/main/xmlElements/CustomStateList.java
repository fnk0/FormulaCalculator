package com.gabilheri.formulacalculator.main.xmlElements;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;

import com.gabilheri.formulacalculator.main.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/9/14.
 */
public class CustomStateList extends StateListDrawable {

    private int backgroundColor, highlightColor;
    private int stateFocused = android.R.attr.state_focused;
    private int statePressed = android.R.attr.state_pressed;
    private int stateSelected = android.R.attr.state_selected;
    private ShapeDrawable mCircleShape, mSquareShape;


    public CustomStateList(Context mContext, int backgroundColor, int highlightColor) {
        this.backgroundColor = backgroundColor;
        this.highlightColor = highlightColor;
        float[] outR = new float[] {10,10,10,10,10,10,10,10};
        mCircleShape = new ShapeDrawable();
        mCircleShape.setShape(new OvalShape());
        mCircleShape.setColorFilter(mContext.getResources().getColor(R.color.def_button), PorterDuff.Mode.CLEAR);
        mCircleShape.getPaint().setColor(mContext.getResources().getColor(R.color.def_button_pressed));

        mSquareShape = new ShapeDrawable();
        mSquareShape.setShape(new RoundRectShape(outR, null, null));
        mSquareShape.getPaint().setColor(backgroundColor);

        this.addState(new int[] {stateFocused }, mCircleShape);
        this.addState(new int[] {statePressed }, mCircleShape);
        this.addState(new int[] {stateSelected }, new ColorDrawable(mContext.getResources().getColor(R.color.light_orange)));
        this.addState(new int[]{}, mSquareShape);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(int highlightColor) {
        this.highlightColor = highlightColor;
    }
}
