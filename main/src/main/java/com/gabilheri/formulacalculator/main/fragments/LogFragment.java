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
import com.gabilheri.formulacalculator.main.cards.LogCard;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.ResultLog;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class LogFragment extends Fragment {

    private List<Card> mCardsList;
    private DatabaseHelper dbHelper;
    private CardListView logsList;
    private List<ResultLog> resultLogs;
    private Runnable run;
    private CardArrayAdapter mCardAdapter;

    /**
     *
     * @param inflater
     *         The LayoutInflater object that can be used to inflate
     *         any views in the fragment,
     * @param container
     *         If non-null, this is the parent view that the fragment's
     *         UI should be attached to.  The fragment should not add the view itself,
     *         but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState
     *         If non-null, this fragment is being re-constructed
     *         from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        dbHelper = new DatabaseHelper(getActivity().getApplicationContext());

        ActionBar mBar = getActivity().getActionBar();

        mBar.setIcon(R.drawable.ic_log);

        return inflater.inflate(R.layout.fragment_logs, container, false);
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
        LinearLayout logFrag = (LinearLayout) view.findViewById(R.id.logFragment);
        Utils.setInsets(getActivity(), logFrag);

        Theme currentTheme = Utils.getCurrentTheme(getActivity());

        view.setBackgroundColor(currentTheme.getSecondaryColor());

        logsList = (CardListView) view.findViewById(R.id.logList);
        mCardsList = new ArrayList<>();

        resultLogs =  dbHelper.getAllResultLogs();
        Collections.reverse(resultLogs);

        for(ResultLog r : resultLogs) {
            LogCard mCard = new LogCard(getActivity());
            mCard.setId("" + r.getId());
            mCard.setmInput(r.getInput());
            mCard.setmResult(r.getResult());
            mCard.setLogID(r.getId());
            mCardsList.add(mCard);
        }

        mCardAdapter = new CardArrayAdapter(getActivity(), mCardsList);
        mCardAdapter.setEnableUndo(true);

        logsList.setAdapter(mCardAdapter);

        /**
         * Deletes all logs in another Thread to not freeze the UI
         */
        run = new Runnable() {
            @Override
            public void run() {
                resultLogs.clear();
                dbHelper.deleteAllResultLogs();
                mCardsList.clear();
                mCardAdapter.notifyDataSetChanged();
                mCardAdapter.notifyDataSetInvalidated();
                logsList.invalidateViews();
                logsList.refreshDrawableState();
            }
        };
    }

    /**
     * @param menu
     *         The options menu in which you place your items.
     * @param inflater
     *         The inflater for this menu
     * @see #setHasOptionsMenu
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem deleteItem = menu.add(Menu.NONE, R.id.delete, 10, R.string.save_theme);
        deleteItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        deleteItem.setIcon(R.drawable.ic_action_delete);
    }

    /**
     *
     * @param item
     *         The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     *
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                getActivity().runOnUiThread(run);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
