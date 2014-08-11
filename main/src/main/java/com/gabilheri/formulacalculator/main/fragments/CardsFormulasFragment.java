package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.cards.CircleCard;
import com.gabilheri.formulacalculator.main.cards.CustomCard;
import com.gabilheri.formulacalculator.main.cards.SquareCard;
import com.gabilheri.formulacalculator.main.formulas.Formula;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/4/14
 */
public class CardsFormulasFragment extends Fragment {

    private ArrayList<Card> mCards;
    private LinearLayout toAddStuff;
    private CardListView mCardList;
    private ArrayList<Formula> mFormulas;
    private ActionBar mActionBar;

    public CardsFormulasFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        toAddStuff = (LinearLayout) inflater.inflate(R.layout.fragment_formulas, null);
        Utils.setInsets(getActivity(), toAddStuff);
        mCardList = (CardListView) toAddStuff.findViewById(R.id.testList);
        mCards = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            CustomCard mCard = new CircleCard(getActivity());
            CustomCard sCard = new SquareCard(getActivity());
            mCards.add(sCard);
            mCards.add(mCard);
        }

        mCardList.setAdapter(new CardArrayAdapter(getActivity(), mCards));

        return toAddStuff;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Utils.getCurrentTheme(getActivity()).getDisplayColor());

        mActionBar = getActivity().getActionBar();
        mActionBar.setIcon(R.drawable.ic_formula);
    }
}
