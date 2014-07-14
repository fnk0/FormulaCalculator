package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.view.View;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/14/14.
 */
public class AddThemeCard extends Card implements Card.OnCardClickListener {

    private Context mContext;

    /**
     *
     * @param context
     */
    public AddThemeCard(Context context) {
        super(context, R.layout.add_theme_card);
        this.mContext = context;
        this.setOnClickListener(this);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public AddThemeCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void onClick(Card card, View view) {

        MainActivity mActivity = (MainActivity) mContext;
        mActivity.displayView(MainActivity.THEME_CREATOR, null);
    }
}
