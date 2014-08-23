package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gabilheri.formulacalculator.main.R;


import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/14/14.
 */
public class FormulaCard extends Card implements Card.OnCardClickListener {

    private String formulaTitle, formulaText;
    private int formulaDrawable;

    public FormulaCard(Context context) {
        super(context, R.layout.formula_card);
    }

    public FormulaCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        TextView formulaTitleView = (TextView) view.findViewById(R.id.formulaTitle);
        TextView formulaContentView = (TextView) view.findViewById(R.id.formulaContent);
        ImageView formulaImage = (ImageView) view.findViewById(R.id.formulaImage);
        formulaTitleView.setText(formulaTitle);
        formulaContentView.setText(formulaText);
        formulaImage.setImageResource(formulaDrawable);
    }

    @Override
    public void onClick(Card card, View view) {


    }

    public String getFormulaTitle() {
        return formulaTitle;
    }

    public void setFormulaTitle(String formulaTitle) {
        this.formulaTitle = formulaTitle;
    }

    public String getFormulaText() {
        return formulaText;
    }

    public void setFormulaText(String formulaText) {
        this.formulaText = formulaText;
    }

    public int getFormulaDrawable() {
        return formulaDrawable;
    }

    public void setFormulaDrawable(int formulaDrawable) {
        this.formulaDrawable = formulaDrawable;
    }
}
