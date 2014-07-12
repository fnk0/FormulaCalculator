package com.gabilheri.formulacalculator.main.xmlElements;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/9/14.
 */
public class CustomStateList extends StateListDrawable {

    private static final String LOG_TAG = "StatesList";
    private int backgroundColor, highlightColor, selectedColor;
    private int stateFocused = android.R.attr.state_focused;
    private int statePressed = android.R.attr.state_pressed;
    private int stateSelected = android.R.attr.state_selected;
    private ShapeDrawable mCircleShape, mSquareShape;
    private LayerDrawable mLayer;
    private AnimationDrawable mAnimation;
    final Animation in = new AlphaAnimation(0.0f, 1.0f);
    final Animation out = new AlphaAnimation(1.0f, 0.0f);

    /**
     *
     * A simple States List for the DefaultButton. Needs to be done programaticaly instead of XML
     * Because the colors will come from the Themes database
     *
     * @param mContext
     *          The contextin which this is being called
     * @param backgroundColor
     *          The background color for the Button
     * @param highlightColor
     *          The hightlited color for the circle drawable that will appear
     * @param selectedColor
     *          The color to be set whenever this button has a selected state
     */
    public CustomStateList(Context mContext, int backgroundColor, int highlightColor, int selectedColor) {
        this.backgroundColor = backgroundColor;
        this.highlightColor = highlightColor;
        this.selectedColor = selectedColor;

        mAnimation = new AnimationDrawable();

        mCircleShape = new ShapeDrawable();
        mCircleShape.setShape(new OvalShape());
        mCircleShape.getPaint().setColor(highlightColor);

        mSquareShape = new ShapeDrawable();
        mSquareShape.setShape(new RectShape());
        mSquareShape.getPaint().setColor(backgroundColor);

        Drawable[] layers = new Drawable[] {mSquareShape, mCircleShape};
        mLayer = new LayerDrawable(layers);

        mAnimation.setOneShot(true);
        mAnimation.addFrame(mLayer, 1000);
        mAnimation.addFrame(mSquareShape, 1);
        mAnimation.setVisible(true, true);

        this.addState(new int[] {stateFocused }, mAnimation);
        this.addState(new int[] {statePressed }, mAnimation);
        this.addState(new int[] {stateSelected }, new ColorDrawable(selectedColor));
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

    @Override
    protected boolean onStateChange(int[] stateSet) {
        for(int state : stateSet) {
            if(state == statePressed) {
                mAnimation.start();
            }
        }
        return super.onStateChange(stateSet);
    }
}
