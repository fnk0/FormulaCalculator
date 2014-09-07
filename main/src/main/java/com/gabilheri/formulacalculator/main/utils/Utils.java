package com.gabilheri.formulacalculator.main.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
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

    public static final int NOT_RESIZE = 0;
    public static final int RESIZE_ONCE = 1;
    public static final int RESIZE_TWICE = 2;
    public static final int RESIZE_THIRD = 3;
    public static final int RESIZE_FOURTH = 4;

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
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     *
     * @param context
     * @return
     */
    public static double getTaxVaue(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String taxValue = prefs.getString(context.getString(R.string.tax_key), context.getString(R.string.tax_def_value));

        return 1 + (Double.parseDouble(taxValue.replaceAll("%", "").replaceAll(" ", "")) / 100);
    }

    /**
     * Returns the typeface based on the stored value in Shared Preferences
     * @param context
     * @return
     */
    public static Typeface getTypeface(Context context) {
        Typeface selectedTypeface = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getString(R.string.font_family_key), context.getString(R.string.font_family_default_value));
        Log.i(LOG_TAG, "Pref Value: " + prefValue);
        if(prefValue.equals(context.getString(R.string.font_light))) {
            selectedTypeface = Typeface.create("sans-serif-light", Typeface.NORMAL);
        } else if(prefValue.equals(context.getString(R.string.font_normal))) {
            selectedTypeface = Typeface.create("sans-serif", Typeface.NORMAL);
        } else if(prefValue.equals(context.getString(R.string.font_thin))) {
            selectedTypeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);
        } else if(prefValue.equals(context.getString(R.string.font_cond))) {
            selectedTypeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        } else {
            selectedTypeface = Typeface.create("sans-serif-light", Typeface.NORMAL);
        }

        return selectedTypeface;
    }

    /**
     * Returns the typeface based on the passed string value
     * @param context
     * @param typefacename
     * @return
     */
    public static Typeface getTypeface(Context context, String typefacename) {
        Typeface selectedTypeface;
        String prefValue = typefacename;
        Log.i(LOG_TAG, "Pref Value: " + prefValue);
        if(prefValue.equals(context.getString(R.string.font_light))) {
            selectedTypeface = Typeface.create("sans-serif-light", Typeface.NORMAL);
        } else if(prefValue.equals(context.getString(R.string.font_normal))) {
            selectedTypeface = Typeface.create("sans-serif", Typeface.NORMAL);
        } else if(prefValue.equals(context.getString(R.string.font_thin))) {
            selectedTypeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);
        } else if(prefValue.equals(context.getString(R.string.font_cond))) {
            selectedTypeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        } else {
            selectedTypeface = Typeface.create("sans-serif-light", Typeface.NORMAL);
        }

        return selectedTypeface;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getButtonTextSize(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.buttons_font_size_key), context.getResources().getString(R.string.font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.button_text_size_x_small);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.button_text_size_small);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.button_text_size);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.button_text_size_large);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.button_text_size_xlarge);
        }

        return 0;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getSpecialButtonTextSize(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.buttons_font_size_key), context.getResources().getString(R.string.font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.button_special_size_x_small);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.button_special_size_small);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.button_special_size);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.button_special_size_large);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.button_special_size_xlarge);
        }

        return 0;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getDeleteButtonTextSize(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.buttons_font_size_key), context.getResources().getString(R.string.font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.delete_button_text_size_x_small);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.delete_button_text_size_small);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.delete_button_text_size);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.delete_button_text_size_large);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.delete_button_text_size_xlarge);
        }

        return 0;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getAnswerButtonTextSize(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.buttons_font_size_key), context.getResources().getString(R.string.font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.ans_button_text_size_x_small);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.ans_button_text_size_small);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.ans_button_text_size);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.ans_button_text_size_large);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.ans_button_text_size_xlarge);
        }

        return 0;
    }



    /**
     *
     * @param context
     * @return
     */
    public static float getDisplayTextSize(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.display_font_key), context.getResources().getString(R.string.display_font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.display_text_xsmall);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.display_text_small);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.display_text_default);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.display_text_large);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.display_text_xlarge);
        }

        return 0;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getDisplayResizedText(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.display_font_key), context.getResources().getString(R.string.display_font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.display_text_xsmall_resized);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.display_text_small_resized);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.display_text_default_resized);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.display_text_large_resized);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.display_text_xlarge_resized);
        }

        return 0;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getDisplayResizedTextTwice(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.display_font_key), context.getResources().getString(R.string.display_font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.display_text_xsmall_resized_twice);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.display_text_small_resized_twice);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.display_text_default_resized_twice);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.display_text_large_resized_twice);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.display_text_xlarge_resized_twice);
        }

        return 0;
    }

    /**
     *
     * @param context
     * @return
     */
    public static float getDisplayResizedTextThird(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.display_font_key), context.getResources().getString(R.string.display_font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.display_text_xsmall_resized_third);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.display_text_small_resized_third);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.display_text_default_resized_third);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.display_text_large_resized_third);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.display_text_xlarge_resized_third);
        }

        return 0;
    }

    public static float getDisplayResizedTextFourth(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String prefValue = prefs.getString(context.getResources().getString(R.string.display_font_key), context.getResources().getString(R.string.display_font_size_def));

        if(prefValue.equals(context.getString(R.string.xsmall))) {
            return context.getResources().getDimension(R.dimen.display_text_xsmall_resized_fourth);
        } else if(prefValue.equals(context.getString(R.string.small))) {
            return context.getResources().getDimension(R.dimen.display_text_small_resized_fourth);
        } else if(prefValue.equals(context.getResources().getString(R.string.font_size_def))) {
            return context.getResources().getDimension(R.dimen.display_text_default_resized_fourth);
        } else if(prefValue.equals(context.getString(R.string.large))) {
            return context.getResources().getDimension(R.dimen.display_text_large_resized_fourth);
        } else if(prefValue.equals(context.getResources().getString(R.string.xlarge))) {
            return context.getResources().getDimension(R.dimen.display_text_xlarge_resized_fourth);
        }

        return 0;
    }

    /**
     * Handy method that will calculate the current size of the text and the display size.
     * And them determine if the text should be resized or not
     * @param context
     * @param textWidth
     * @return
     */
    public static boolean shouldResizeText(Context context, int textWidth) {

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int displayWidth = displayMetrics.widthPixels;

        //Log.i(LOG_TAG, "Display Width: " + displayWidth + " --- Text Size: " + textWidth);

        if(displayWidth - textWidth < 80) {
            //Log.i(LOG_TAG, "Resizing!!");
            return true;
        }

        return false;
    }

    /**
     *
     * @param context
     * @param input
     */
    public static void setCurrentCalcInput(Context context, String input) {
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.current_input_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(context.getString(R.string.current_input), input);
        editor.apply();
    }

    public static String getCurrentCalcInput(Context context) {
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.current_input_key), Context.MODE_PRIVATE);
        return pref.getString(context.getString(R.string.current_input), "");
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
