package com.gabilheri.formulacalculator.main.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.gabilheri.formulacalculator.main.R;


/**
 * Created by marcus on 5/16/14.
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since May, 2014
 */
public class VariablesDialog extends DialogFragment {

    public static int STORE_DIALOG = 0;
    public static int RELEASE_DIALOG = 1;
    private Button var0, var1, var2, var3, var4, var5, var6, var7, var8, var9;
    private int type;
    private String resultString;

    /**
     * Default Constructor
     */
    public VariablesDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_storevar, null);

        Bundle bundle = this.getArguments();
        resultString = bundle.getString("result");
        type = bundle.getInt("type");
        initializeViews(layout);

        Button resetVars = (Button) layout.findViewById(R.id.resetStoreVar);

        if (type == RELEASE_DIALOG) {
            resetVars.setText("Dismiss");
        }

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
        var0 = (Button) view.findViewById(R.id.storeVar0);
        var0.setText(preferences.getString("var0", "Empty"));
        var1 = (Button) view.findViewById(R.id.storeVar1);
        var1.setText(preferences.getString("var1", "Empty"));
        var2 = (Button) view.findViewById(R.id.storeVar2);
        var2.setText(preferences.getString("var2", "Empty"));
        var3 = (Button) view.findViewById(R.id.storeVar3);
        var3.setText(preferences.getString("var3", "Empty"));
        var4 = (Button) view.findViewById(R.id.storeVar4);
        var4.setText(preferences.getString("var4", "Empty"));
        var5 = (Button) view.findViewById(R.id.storeVar5);
        var5.setText(preferences.getString("var5", "Empty"));
        var6 = (Button) view.findViewById(R.id.storeVar6);
        var6.setText(preferences.getString("var6", "Empty"));
        var7 = (Button) view.findViewById(R.id.storeVar7);
        var7.setText(preferences.getString("var7", "Empty"));
        var8 = (Button) view.findViewById(R.id.storeVar8);
        var8.setText(preferences.getString("var8", "Empty"));
        var9 = (Button) view.findViewById(R.id.storeVar9);
        var9.setText(preferences.getString("var9", "Empty"));
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
                case R.id.resetStoreVar:
                    editor.clear();
                    break;
            }
        }
        editor.commit();
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

        int id = view.getId();
        String result = "";

        switch (id) {
            case R.id.storeVar0:
                result += 0;
                break;
            case R.id.storeVar1:
                result += 1;
                break;
            case R.id.storeVar2:
                result += 2;
                break;
            case R.id.storeVar3:
                result += 3;
                break;
            case R.id.storeVar4:
                result += 4;
                break;
            case R.id.storeVar5:
                result += 5;
                break;
            case R.id.storeVar6:
                result += 6;
                break;
            case R.id.storeVar7:
                result += 7;
                break;
            case R.id.storeVar8:
                result += 8;
                break;
            case R.id.storeVar9:
                result += 9;
                break;
            case R.id.resetStoreVar:
                dismiss();
                return "";
        }
        dismiss();
        if (result.equals("Empty")) {
            return "";
        }
        return "var(" + result + ")";
    }
}

