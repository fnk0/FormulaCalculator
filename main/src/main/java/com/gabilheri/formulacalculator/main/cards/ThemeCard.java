package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/13/14.
 */
public class ThemeCard extends Card implements Card.OnSwipeListener, Card.OnCardClickListener, Card.OnUndoSwipeListListener {

    private String themeName;
    private long themeID;
    private int primaryColor, secondaryColor, selectedColor, displayColor;
    private Context mContext;

    public ThemeCard(Context context) {
        super(context, R.layout.theme_card);
        this.mContext = context;
    }

    public ThemeCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        TextView textThemeName = (TextView) view.findViewById(R.id.themeName);
        TextView textPrimaryColor = (TextView) view.findViewById(R.id.primaryColor);
        TextView textSecondaryColor = (TextView) view.findViewById(R.id.secondaryColor);
        TextView textSelectedColor = (TextView) view.findViewById(R.id.selectedColor);
        TextView textDisplayColor = (TextView) view.findViewById(R.id.displayColor);

        textThemeName.setText(Html.fromHtml(themeName));
        textPrimaryColor.setBackgroundColor(primaryColor);
        textSecondaryColor.setBackgroundColor(secondaryColor);
        textSelectedColor.setBackgroundColor(selectedColor);
        textDisplayColor.setBackgroundColor(displayColor);
    }

    @Override
    public void onClick(Card card, View view) {

    }

    @Override
    public void onSwipe(Card card) {

    }

    @Override
    public void onUndoSwipe(Card card) {

    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public long getThemeID() {
        return themeID;
    }

    public void setThemeID(long themeID) {
        this.themeID = themeID;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public int getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(int displayColor) {
        this.displayColor = displayColor;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
