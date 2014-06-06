package com.gabilheri.formulacalculator.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gabilheri.formulacalculator.main.R;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/4/14
 */
public class CardsFormulasFragment extends Fragment {

    private ArrayList<Card> mCards;
    private LinearLayout toAddStuff;
    private CardListView mCardList;
    public CardsFormulasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        toAddStuff = (LinearLayout) inflater.inflate(R.layout.fragment_formulas, null);
        mCardList = (CardListView) toAddStuff.findViewById(R.id.testList);
        mCards = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Card mCard = new Card(getActivity(), R.layout.row_card);
            mCard.setTitle("Sample Card " + i);
            CardThumbnail mThumbnail = new CardThumbnail(getActivity());
            mThumbnail.setDrawableResource(R.drawable.ic_calculator);
            mCard.addCardThumbnail(mThumbnail);
            mCards.add(mCard);
        }

        mCardList.setAdapter(new CardArrayAdapter(getActivity(), mCards));

        return toAddStuff;
    }
}
