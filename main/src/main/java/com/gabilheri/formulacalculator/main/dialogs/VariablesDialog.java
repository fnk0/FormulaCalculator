package com.gabilheri.formulacalculator.main.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.gabilheri.formulacalculator.main.utils.Utils;
import com.gabilheri.formulacalculator.main.xmlElements.DefaultButton;

import java.util.ArrayList;


/**
 * Created by marcus on 5/16/14.
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May, 2014
 */
public class VariablesDialog extends DialogFragment implements View.OnClickListener {

    public static int STORE_DIALOG = 0;
    public static int RELEASE_DIALOG = 1;
    private DefaultButton var0, var1, var2, var3, var4, var5, var6, var7, var8, var9;
    private int type;
    private String resultString;
    private ArrayList<DefaultButton> mButtons, mNumbers;
    private Theme currentTheme;

    /**
     * Default Constructor
     */
    public VariablesDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_storevar, null);

        currentTheme = Utils.getCurrentTheme(getActivity());

        TextView dialogTitle = (TextView) layout.findViewById(R.id.dialogTitle);
        dialogTitle.setTextColor(currentTheme.getDisplayTextColor());

        LinearLayout dialogRoot = (LinearLayout) layout.findViewById(R.id.dialogRoot);
        dialogRoot.setBackgroundColor(currentTheme.getDisplayColor());

        mButtons = new ArrayList<>();
        mNumbers = new ArrayList<>();

        Bundle bundle = this.getArguments();
        resultString = bundle.getString("result");
        type = bundle.getInt("type");
        initializeViews(layout);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        return builder.create();
    }

    /**
     * Helper method to initialize all the TextViews and set the current value of the stored vars
     * @param view the layout of the dialog.
     */
    private void initializeViews(View view) {
        SharedPreferences preferences = getActivity().getSharedPreferences("storeVar", Context.MODE_PRIVATE);

        var0 = (DefaultButton) view.findViewById(R.id.storeVar0);
        var0.setText(preferences.getString("var0", "Empty"));
        var1 = (DefaultButton) view.findViewById(R.id.storeVar1);
        var1.setText(preferences.getString("var1", "Empty"));
        var2 = (DefaultButton) view.findViewById(R.id.storeVar2);
        var2.setText(preferences.getString("var2", "Empty"));
        var3 = (DefaultButton) view.findViewById(R.id.storeVar3);
        var3.setText(preferences.getString("var3", "Empty"));
        var4 = (DefaultButton) view.findViewById(R.id.storeVar4);
        var4.setText(preferences.getString("var4", "Empty"));
        var5 = (DefaultButton) view.findViewById(R.id.storeVar5);
        var5.setText(preferences.getString("var5", "Empty"));
        var6 = (DefaultButton) view.findViewById(R.id.storeVar6);
        var6.setText(preferences.getString("var6", "Empty"));
        var7 = (DefaultButton) view.findViewById(R.id.storeVar7);
        var7.setText(preferences.getString("var7", "Empty"));
        var8 = (DefaultButton) view.findViewById(R.id.storeVar8);
        var8.setText(preferences.getString("var8", "Empty"));
        var9 = (DefaultButton) view.findViewById(R.id.storeVar9);
        var9.setText(preferences.getString("var9", "Empty"));

        mButtons.add(var0);
        mButtons.add(var1);
        mButtons.add(var2);
        mButtons.add(var3);
        mButtons.add(var4);
        mButtons.add(var5);
        mButtons.add(var6);
        mButtons.add(var7);
        mButtons.add(var8);
        mButtons.add(var9);

        int counter = 0;
        for(DefaultButton b : mButtons) {
            b.setText(preferences.getString("var" + counter, "Empty"));
            b.setOnClickListener(this);
            b.setTextSize(getResources().getDimension(R.dimen.button_text_size_small));
            b.setCustomBackgroundColor(currentTheme.getPrimaryColor());
            b.setHighlightColor(currentTheme.getPrimaryHighlightColor());
            b.setTextColor(currentTheme.getPrimaryButtonTextColor());
        }

    }

    @Override
    public void onClick(View v) {
        handleVar(v);
    }

    /**
     * @param view the pressed button
     * @return
     *          If is a store dialog returns null
     *          Otherwise returns var(storedVar)
     */
    public String handleVar(View view) {
        if (type == STORE_DIALOG) {
            return storeVar(view);
        } else if (type == RELEASE_DIALOG) {
            return releaseVar(view);
        }
        return null;
    }

    /**
     * Method to store the result from the result box to the variable
     * The variables are stored in a sharedPreference with the name of storeVar
     * the variables are stored with the keys var + number
     * @param view the pressed button
     * @return null
     */
    public String storeVar(View view) {

        int id = view.getId();

        SharedPreferences preferences = getActivity().getSharedPreferences("storeVar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (resultString != null) {
            if (resultString.equals("")) {
                resultString = "Empty";
            }
            switch (id) {
                case R.id.storeVar0:
                    editor.putString("var0", resultString);
                    break;
                case R.id.storeVar1:
                    editor.putString("var1", resultString);
                    break;
                case R.id.storeVar2:
                    editor.putString("var2", resultString);
                    break;
                case R.id.storeVar3:
                    editor.putString("var3", resultString);
                    break;
                case R.id.storeVar4:
                    editor.putString("var4", resultString);
                    break;
                case R.id.storeVar5:
                    editor.putString("var5", resultString);
                    break;
                case R.id.storeVar6:
                    editor.putString("var6", resultString);
                    break;
                case R.id.storeVar7:
                    editor.putString("var7", resultString);
                    break;
                case R.id.storeVar8:
                    editor.putString("var8", resultString);
                    break;
                case R.id.storeVar9:
                    editor.putString("var9", resultString);
                    break;
            }
        }
        editor.apply();
        dismiss();
        return null;
    }

    /**
     * Method to handle the release of a stored variable to the input box.
     * The custom Function to handle the release of the variable is var()
     *
     * @param view the pressed button
     * @return the String result that will be pasted to the inputbox
     */
    public String releaseVar(View view) {

        String result = (((Button) view).getText().toString());
        dismiss();
        if (result.equals("Empty")) {
            return "";
        } else {
            return result;
        }
    }
}

