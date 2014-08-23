package com.gabilheri.formulacalculator.main.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.cards.AddThemeCard;
import com.gabilheri.formulacalculator.main.cards.ThemeCard;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 *          since 6/29/14.
 */
public class ThemesFragment extends Fragment {

    private List<Card> mCardList;
    private DatabaseHelper dbHelper;
    private CardListView mCardsListView;
    private List<Theme> themesList;
    private CardArrayAdapter mCardAdapter;
    private Runnable run;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());

        ActionBar mBar = getActivity().getActionBar();
        mBar.setIcon(R.drawable.ic_themes);

        return inflater.inflate(R.layout.fragment_themes, container, false);

    }

    /**
     * @param savedInstanceState
     *         If the fragment is being re-created from
     *         a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param view
     *         The View returned by {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}.
     * @param savedInstanceState
     *         If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view.setBackgroundColor(Utils.getCurrentTheme(getActivity()).getPrimaryColor());

        LinearLayout themesFrag = (LinearLayout) view.findViewById(R.id.themesFrag);
        Utils.setInsets(getActivity(), themesFrag);

        mCardsListView = (CardListView) view.findViewById(R.id.themesList);
        mCardList = new ArrayList<>();

        themesList = dbHelper.getAllThemesForUser("user");

        mCardList.add(new AddThemeCard(getActivity()));

        if(themesList != null) {
            for(Theme mTheme : themesList) {
                ThemeCard mCard = new ThemeCard(getActivity());
                mCard.setId("" + mTheme.getId());
                mCard.setThemeName(mTheme.getThemeName());
                mCard.setPrimaryColor(mTheme.getPrimaryColor());
                mCard.setSecondaryColor(mTheme.getSecondaryColor());
                mCard.setSelectedColor(mTheme.getDisplayTextColor());
                mCard.setDisplayColor(mTheme.getDisplayColor());
                mCard.setThemeID(mTheme.getId());
                mCard.setUsername(mTheme.getUsername());
                mCardList.add(mCard);
            }
        }

        mCardAdapter = new CardArrayAdapter(getActivity(), mCardList);
        mCardsListView.setAdapter(mCardAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
