package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.internal.overflowanimation.TwoCardOverlayAnimation;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/6/14
 */
public class CustomCard extends Card implements Card.OnCardClickListener {

    private ImageView mThumbnailView;
    private TextView formulaTitle;
    private String mTitle;
    private Context context;
    private int mThumbnail;
    private CustomCardHeader mHeader;

    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public CustomCard(Context context) {
        this(context, R.layout.row_card);
        this.setOnClickListener(this);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
        this.context = context;
        initCard();
    }

    private void initCard() {
        mHeader = new CustomCardHeader(context);
        this.addCardHeader(mHeader);

        CustomExpandCard expand = new CustomExpandCard(context);
        expand.setTitle("Expand Card");
        this.addCardExpand(expand);
    }

    public CustomCard setmTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public CustomCard setmThumbnail(int mThumbnail) {
        this.mThumbnail = mThumbnail;
        return this;
    }

    @Override
    public void onClick(Card card, View view) {
        Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show();
        this.setExpanded(true);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mThumbnailView = (ImageView) parent.findViewById(R.id.thumbnailFormula);
        formulaTitle = (TextView) parent.findViewById(R.id.formulaTitle);


        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(parent);
        setViewToClickToExpand(viewToClickToExpand);


        if (mThumbnailView != null && formulaTitle != null) {
            mThumbnailView.setImageResource(mThumbnail);
            formulaTitle.setText(mTitle);
        }
    }

    /**
     *
     */
    public class CustomExpandCard extends CardExpand {
        //Use your resource ID for your inner layout
        public CustomExpandCard(Context context) {
            super(context, R.layout.card_square);
        }


    }
}
