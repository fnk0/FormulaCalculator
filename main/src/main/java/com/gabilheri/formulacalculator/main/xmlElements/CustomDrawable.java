package com.gabilheri.formulacalculator.main.xmlElements;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/14/14.
 */
class CustomDrawable extends Drawable implements ValueAnimator.AnimatorUpdateListener {

    private int statePressed;
    private float radius = 0;
    private ValueAnimator animator = new ValueAnimator();
    private Paint paint = new Paint();
    private RectF rect = new RectF();

    public CustomDrawable() {
        animator.addUpdateListener(this);
        paint.setColor(Color.RED);
    }

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    protected boolean onStateChange(int[] state) {
        int isPressed = 0;
        for (int st : state) {
            if (st == android.R.attr.state_pressed) {
                isPressed = 1;
                break;
            }
        }
        if ((isPressed ^ statePressed) != 0) {
            statePressed ^= 1;
            if (animator.isRunning()) {
                animator.cancel();
            }
            float w2 = getBounds().width() / 2;
            float endRadius = statePressed == 1? w2 : 0;
            animator.setFloatValues(radius, endRadius);
            animator.setDuration((long) (200 * Math.abs(radius - endRadius) / w2));
            animator.start();
        }
        return true;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        radius = (Float) animation.getAnimatedValue();
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        rect.set(getBounds());
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
