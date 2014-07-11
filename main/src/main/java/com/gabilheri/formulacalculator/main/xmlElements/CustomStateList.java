package com.gabilheri.formulacalculator.main.xmlElements;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;

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
    private LayerDrawable mLayer;


    public CustomStateList(Context mContext, int backgroundColor, int highlightColor) {
        this.backgroundColor = backgroundColor;
        this.highlightColor = highlightColor;

        mCircleShape = new ShapeDrawable();
        mCircleShape.setShape(new OvalShape());
        mCircleShape.getPaint().setColor(highlightColor);

        mSquareShape = new ShapeDrawable();
        mSquareShape.setShape(new RectShape());
        mSquareShape.getPaint().setColor(backgroundColor);

        Drawable[] layers = new Drawable[] {mSquareShape, mCircleShape};
        mLayer = new LayerDrawable(layers);

        this.addState(new int[] {stateFocused }, mLayer);
        this.addState(new int[] {statePressed }, mLayer);
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
