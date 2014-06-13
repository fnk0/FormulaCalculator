package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/8/14
 */
public class CustomCardHeader extends CardHeader implements View.OnClickListener {

    private String mTitle;
    private Context context;

    /**
     *
     * @param context
     */
    public CustomCardHeader(Context context) {
        super(context, R.layout.card_header);
        this.context = context;
        this.setButtonExpandVisible(true);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CustomCardHeader(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    /**
     *
     * @param mTitle
     * @return
     */
    public CustomCardHeader setmTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    /**
     *
     * @param parent
     * @param view
     */
    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        RadioButton formulaFavorite = (RadioButton) parent.findViewById(R.id.formulaFavorite);
        TextView formulaTitle = (TextView) parent.findViewById(R.id.formulaTitle);
        ImageButton formulaOverflow = (ImageButton) parent.findViewById(R.id.formulaOverflow);

        formulaFavorite.setOnClickListener(this);
        formulaOverflow.setOnClickListener(this);

        if(formulaTitle != null && mTitle != null) {
            formulaTitle.setText(mTitle);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *         The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.formulaFavorite) {
            Toast.makeText(getContext(), "Favorite!", Toast.LENGTH_SHORT).show();
            Log.i("HEADER", "HELLO FAVORITE!");
        } else if(id == R.id.formulaOverflow) {
            Toast.makeText(getContext(), "Overflow!", Toast.LENGTH_SHORT).show();
            Log.i("HEADER", "HELLO OVERFLOW!");
        }
    }
}
