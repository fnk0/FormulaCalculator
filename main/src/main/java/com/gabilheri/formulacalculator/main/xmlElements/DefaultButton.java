package com.gabilheri.formulacalculator.main.xmlElements;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private Animation zoom;

    private float mDownX;
    private float mDownY;

    private float mRadius;

    private Paint mPaint;

    /**
     *
     * @param context
     */
    public DefaultButton(Context context) {
        super(context);
        init(context);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public DefaultButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     *
     * @param context
     *          The context in which this button is being called
     * @param attrs
     *          Used internally by Android
     */
    public DefaultButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAlpha(100);
        textColor = context.getResources().getColor(R.color.list_background);
        backgroundColor = context.getResources().getColor(R.color.def_button);
        highlightColor = context.getResources().getColor(R.color.def_button_pressed);
        selectedColor = context.getResources().getColor(R.color.light_orange);

        this.mContext = context;
        zoom = AnimationUtils.loadAnimation(mContext, R.anim.zoom);
        /**
         * This is necessary so the Layout editor can render this button in real time.
         */
        if(!this.isInEditMode()) {
            this.setOnClickListener(this);
            this.mActivity = (MainActivity) context;
            this.setTextSize(getResources().getDimension(R.dimen.button_text_size));
            this.setTextColor(textColor);
            this.setBackground(new CustomStateList(context, backgroundColor, highlightColor, selectedColor));
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

    @Override
    public void onClick(View v) {
        //startAnimation(zoom);
        mActivity.handleKeypad(this);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            mDownX = event.getX();
            mDownY = event.getY();

            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "radius", 0,
                    getWidth() * 3.0f);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(400);
            animator.start();
        }
        return super.onTouchEvent(event);
    }

    public void setRadius(final float radius) {
        mRadius = radius;
        if (mRadius > 0) {
            RadialGradient radialGradient = new RadialGradient(mDownX, mDownY,
                    mRadius * 3, Color.TRANSPARENT, Color.BLACK,
                    Shader.TileMode.MIRROR);
            mPaint.setShader(radialGradient);
        }
        invalidate();
    }

    private Path mPath = new Path();
    private Path mPath2 = new Path();

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        mPath2.reset();
        mPath2.addCircle(mDownX, mDownY, mRadius, Path.Direction.CW);

        canvas.clipPath(mPath2);

        mPath.reset();
        mPath.addCircle(mDownX, mDownY, mRadius / 3, Path.Direction.CW);

        canvas.clipPath(mPath, Region.Op.DIFFERENCE);

        canvas.drawCircle(mDownX, mDownY, mRadius, mPaint);
    }
}
