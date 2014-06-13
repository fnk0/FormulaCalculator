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
    private LinearLayout toAddContent;
    private int innerContentLayout;
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
        mHeader.setTitle(mTitle);
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

    public CustomCard setInnerContentLayout(int innerContentLayout) {
        this.innerContentLayout = innerContentLayout;
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
        toAddContent = (LinearLayout) parent.findViewById(R.id.formulaContent);

        /*
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(mStar);
        setViewToClickToExpand(viewToClickToExpand);
        */

        if (mThumbnailView != null && toAddContent != null) {
            mThumbnailView.setImageResource(mThumbnail);
            try {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(innerContentLayout, null);
                if (layout != null) {
                    toAddContent.removeAllViews();
                    toAddContent.addView(layout);
                }
            } catch (Exception ex) {}

        }
    }

    public class SimpleStockAnimation extends TwoCardOverlayAnimation {

        public SimpleStockAnimation(Context context, Card card) {
            super(context, card);
        }

        @Override
        protected CardInfoToAnimate setCardToAnimate(Card card) {
            TwoCardToAnimate info = new TwoCardToAnimate() {
                @Override
                public int getLayoutIdToAdd() {
                    return R.layout.overflow_layout;
                }
            };
            return info;
        }
    }

    public class CustomExpandCard extends CardExpand {
        //Use your resource ID for your inner layout
        public CustomExpandCard(Context context) {
            super(context, R.layout.card_square);
        }


    }
}
