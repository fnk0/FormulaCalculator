package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.cards.FormulaCard;
import com.gabilheri.formulacalculator.main.database.Theme;
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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toAddStuff = (LinearLayout) inflater.inflate(R.layout.fragment_formulas, null);
        Utils.setInsets(getActivity(), toAddStuff);
        mCardList = (CardListView) toAddStuff.findViewById(R.id.testList);
        mCards = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            FormulaCard mCard = new FormulaCard(getActivity());
            mCard.setFormulaTitle("Example Card");
            mCard.setFormulaText("This is where the formula goes.");
            mCard.setFormulaDrawable(R.drawable.ic_circle);
            mCards.add(mCard);
        }

        mCardList.setAdapter(new CardArrayAdapter(getActivity(), mCards));

        return toAddStuff;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Theme currentTheme = Utils.getCurrentTheme(getActivity());
        view.setBackgroundColor(currentTheme.getDisplayColor());

        TextView comingSoon = (TextView) view.findViewById(R.id.comingSoonText);
        comingSoon.setTextColor(currentTheme.getDisplayTextColor());
    }
}
