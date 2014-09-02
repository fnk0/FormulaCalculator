package com.gabilheri.formulacalculator.main.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.database.DatabaseHelper;
import com.gabilheri.formulacalculator.main.database.Theme;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class Utils {

    public static final String LOG_TAG = "UtilsLog";

    /**
     *
     * @param context
     * @return
     */
    public static String[] getParenthesisKeys(Context context) {
        Resources res = context.getResources();
        String[] keys = {
                res.getString(R.string.par1),
                res.getString(R.string.par2),
                res.getString(R.string.par3),
                res.getString(R.string.par4),
                res.getString(R.string.par5),
                res.getString(R.string.par6),
                res.getString(R.string.par7),
                res.getString(R.string.par8),
        };

        return keys;
    }

    /**
     *
     * @param context
     * @return
     */
    public static boolean isKeypadStandard(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.calc_layout_key),
                context.getString(R.string.keypad_standard_value))
                .equals(context.getString(R.string.keypad_standard_value));
    }

    /**
     *
     * @param context
     * @return
     */
    public static double getTaxVaue(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String taxValue = prefs.getString(context.getString(R.string.tax_key), context.getString(R.string.tax_def_value));

        return 1 + (Double.parseDouble(taxValue.replaceAll("%", "")) / 100);
    }

    /**
     *
     * @param context
     * @return
     */
    public static int getPrecisionValue(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String precisionValue = prefs.getString(context.getString(R.string.precision_key), context.getString(R.string.precision_def_value));

        return Integer.parseInt(precisionValue);
    }

    public static DecimalFormat getDecimalFormatForPrecision(int precision) {
        StringBuilder precisionBuilder = new StringBuilder();
        precisionBuilder.append("#0.");


        for(int i = 0; i < precision; i++) {
            precisionBuilder.append("#");
        }

        return new DecimalFormat(precisionBuilder.toString());
    }

    /**
     * Simple method with a regular expression to strip the HTML of a String
     * This method is specially used to remove the HTML needed to format all the
     * colored parenthesys
     *
     * @param toStrip The String that needs html to be stripped
     * @return A string html free!
     */
    public static String stripHTML(String toStrip) {
        return toStrip.replaceAll("\\<.*?>", "");
    }

    /**
     * Helper method to easy squaring a number.
     * Very handful for all the trig functions that involves a lot
     * of squaring numbers such as pytaghorean, etc..
     *
     * @param toSquare The value to be squared
     * @return the squared number
     */
    public static double squareNumber(double toSquare) {
        return Math.pow(toSquare, 2);
    }

    /**
     * Helper function to convert a rgba String to a hexString
     * The hex String can them be displayed or converted to a int represented by the hex value
     *
     * @param rgbaString The String with a color in RGBA format
     * @return a String with a color formated in Hex Value
     */
    public static String rgbaToHex(String rgbaString) {
        String[] rgbColors = rgbaString.split(",");
        String red = pad(Integer.toHexString(Integer.parseInt(rgbColors[0])));
        String green = pad(Integer.toHexString(Integer.parseInt(rgbColors[1])));
        String blue = pad(Integer.toHexString(Integer.parseInt(rgbColors[2])));
        String alpha = pad(Integer.toHexString((int) (Float.parseFloat(rgbColors[3]) * 255)));

        Log.i(LOG_TAG, "Hex String: #" + alpha + red + green + blue);

        return "#" + alpha + red + green + blue;
    }

    /**
     * Helper method to transform a Hex String to it's integer value
     * @param hexString
     * @return
     */
    public static int getHexColor(String hexString) {
        String filtered = hexString.replaceAll("#", "").replaceAll(" ", "");
        if(filtered.length() == 6) {
            filtered = "FF" + filtered;
        }
        return new BigInteger(filtered, 16).intValue();
    }

    /**
     * Helper method to get the current Theme for the app
     * @param mContext
     * @return
     */
    public static Theme getCurrentTheme(Context mContext) {
        SharedPreferences mPreferences = mContext.getSharedPreferences(MainActivity.CURRENT_THEME, Context.MODE_PRIVATE);
        DatabaseHelper dbHelper = new DatabaseHelper(mContext.getApplicationContext());
        return dbHelper.getThemeByName(mPreferences.getString(MainActivity.CURRENT_THEME, MainActivity.CURRENT_THEME));
    }

    private static String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }

    /**
     * Checks if the number before or after a parenthesis is an operator or not.
     * If the number is not an operator a multiplication symbol will be automatically added :D
     *
     * @param c
     * @return
     */
    public static boolean isOperator(char c, Context mContext) {
        if (c == '+' || c == '-' || c == '/' || c == '*' || c == mContext.getString(R.string.sqrt).charAt(0) || c == mContext.getResources().getString(R.string.exp_symbol).charAt(0) || c == '%' ) {
            return true;
        }
        if (c != 'e' && Character.isLetter(c)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the button selected is an Operator or not
     *
     * @param buttonId The ID of the pressed button
     * @return true if is an operator
     */
    public static boolean isButtonOperator(int buttonId) {
        switch (buttonId) {
            case R.id.plus:
            case R.id.minus:
            case R.id.keypadSqrt:
            case R.id.multiply:
            case R.id.divide:
            case R.id.keypadPwr:
            case R.id.btnPercent:
                return true;
            default:
                return false;
        }
    }

    /*
    public static Dialog setDialogColors(Dialog dialog, Context context) {
        Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        // set OK button color here
        okButton.setBackgroundColor(context.getResources().getColor(R.color.color_picker_dialog));

        Button noButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        // set NO button color here
        noButton.setBackgroundColor(context.getResources().getColor(R.color.color_picker_dialog));

        return dialog;
    }
    */

    public static void setInsets(Activity context, View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        SystemBarTintManager tintManager = new SystemBarTintManager(context);
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        view.setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     *
     * @param view
     */
    public static void blink(final View view) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 500;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (view.getVisibility() == View.VISIBLE) {
                            view.setVisibility(View.INVISIBLE);
                        } else {
                            view.setVisibility(View.VISIBLE);
                        }
                        blink(view);
                    }
                });
            }
        }).start();
    }

}
