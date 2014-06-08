package com.gabilheri.formulacalculator.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/4/14
 */
public class Example extends Activity {

    private String URL = "http://myUrl/myFile";
    private JSONParser jsonParser;
    private ProgressDialog pDialog;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MyTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class MyTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Do stuff here before the request such as showing progress bar, dialogs, etc.
            pDialog = new ProgressDialog(Example.this);
            pDialog.setMessage("Scanning ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Object doInBackground(Object... params) {

            // Do the network request here
            int success = 0;
            try {

                List<NameValuePair> stuff = new ArrayList<NameValuePair>();
                stuff.add(new BasicNameValuePair("label",  "data"));

                JSONObject jObject = jsonParser.makeHttpRequest(URL, "POST", stuff);

                success = jObject.getInt(TAG_SUCCESS);

                if(success == 1) {

                    // You got your stuff!
                    // Note that I sent an success field with an integer from my database
                    // you can handle it anyway you want
                    // But is nice to have a flag that tells you if it was successfull or not.

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            // Handle your UI updates with the data from the request here
            pDialog.dismiss();
        }
    }
}
