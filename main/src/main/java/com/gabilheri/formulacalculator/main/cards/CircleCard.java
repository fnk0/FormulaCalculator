package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/6/14
 */
public class CircleCard extends CustomCard {

    private String mTitle;
    private int mThumbnail;

    /**
     *
     * @param context
     */
    public CircleCard(Context context) {
        super(context);
        this.mTitle = context.getResources().getStringArray(R.array.circle)[0];
        this.mThumbnail = R.drawable.ic_circle;
        this.setmTitle(mTitle).setmThumbnail(mThumbnail).setInnerContentLayout(R.layout.card_circle);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CircleCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }


}
