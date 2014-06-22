package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/7/14
 */
public class SquareCard extends CustomCard {

    private String mTitle;
    private int mThumbnail;
    private Context context;

    public SquareCard(Context context) {
        super(context);
        this.context = context;
        this.mTitle = context.getResources().getStringArray(R.array.square)[0];
        this.mThumbnail = R.drawable.ic_square;
        this.setmTitle(mTitle).setmThumbnail(mThumbnail);
    }

    public SquareCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

}
