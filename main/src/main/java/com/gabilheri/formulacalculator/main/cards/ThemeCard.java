package com.gabilheri.formulacalculator.main.cards;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.utils.Utils;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/13/14.
 */
public class ThemeCard extends Card implements Card.OnSwipeListener, Card.OnCardClickListener, Card.OnUndoSwipeListListener {

    private String themeName, username;
    private long themeID;
    private int primaryColor, secondaryColor, selectedColor, displayColor;
    private Context mContext;
    private DatabaseHelper dbHelper;
    private Theme currentTheme;
    private SharedPreferences mPreferences;

    public ThemeCard(Context context) {
        super(context, R.layout.theme_card);
        this.mContext = context;
        setSwipeable(true);
        dbHelper = new DatabaseHelper(context);
        mPreferences = context.getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        currentTheme = dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));
        setOnClickListener(this);
        setOnSwipeListener(this);
    }

    public ThemeCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if(themeName.equals(currentTheme.getThemeName())) {
            view.setBackgroundColor(mContext.getResources().getColor(R.color.theme_selected));
        }

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
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(MainActivity.CURRENT_THEME, themeName);
        editor.apply();

        MainActivity mActivity = (MainActivity) mContext;
        mActivity.displayView(MainActivity.CALCULATOR_FRAG, null);
    }

    @Override
    public void onSwipe(Card card) {
        dbHelper.deleteTheme(themeID, username);
        SharedPreferences mPref = mContext.getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        if(Utils.getCurrentTheme(mContext).getThemeName().equals(themeName)) {
            editor.putString(MainActivity.CURRENT_THEME, Theme.DEFAULT_THEME);
        } else {
            editor.putString(MainActivity.CURRENT_THEME, Utils.getCurrentTheme(mContext).getThemeName());
        }

        editor.apply();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
