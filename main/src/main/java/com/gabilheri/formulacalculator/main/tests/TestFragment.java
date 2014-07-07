package com.gabilheri.formulacalculator.main.tests;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.formulacalculator.main.MainActivity;
import com.gabilheri.formulacalculator.main.R;
import com.gabilheri.formulacalculator.main.formulas.Triangle;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/7/14.
 */
public class TestFragment extends Fragment {

    private static final String LOG_TAG = "TestFragment";

    public TestFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_test, container, false);

        if(MainActivity.DEBUG) {

            Log.i(LOG_TAG, "Triangle AAS 1");
            Triangle mTest = new Triangle(getActivity(), Triangle.TRIANGLE_AAS);
            mTest.setAngleType(Triangle.DEGREES);
            mTest.angleAngleSide(7, 35, 62);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle AAS 2");
            Triangle mTest2 = new Triangle(getActivity(), Triangle.TRIANGLE_AAS);
            mTest2.setAngleType(Triangle.DEGREES);
            mTest2.angleAngleSide(12.6, 41, 105);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle ASA 1");
            Triangle mTest3 = new Triangle(getActivity(), Triangle.TRIANGLE_ASA);
            mTest3.setAngleType(Triangle.DEGREES);
            mTest3.angleSideAngle(76, 34, 9);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle AAS 2");
            Triangle mTest4 = new Triangle(getActivity(), Triangle.TRIANGLE_ASA);
            mTest4.setAngleType(Triangle.DEGREES);
            mTest4.angleSideAngle(87, 42, 18.9);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle SAS 1");
            Triangle mTest5 = new Triangle(getActivity(), Triangle.TRIANGLE_SAS);
            mTest5.setAngleType(Triangle.DEGREES);
            mTest5.sideAngleSide(49, 5, 7);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle SAS 2");
            Triangle mTest6 = new Triangle(getActivity(), Triangle.TRIANGLE_SAS);
            mTest6.setAngleType(Triangle.DEGREES);
            mTest6.sideAngleSide(117, 2.6, 6.9);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle SSA 1");
            Triangle mTest7 = new Triangle(getActivity(), Triangle.TRIANGLE_SSA);
            mTest7.setAngleType(Triangle.DEGREES);
            mTest7.sideSideAngle(8, 13, 31);

            Log.i(LOG_TAG, "-----------------------------------------------------------");
            Log.i(LOG_TAG, "Triangle SSA 2");
            Triangle mTest8 = new Triangle(getActivity(), Triangle.TRIANGLE_SSA);
            mTest8.setAngleType(Triangle.DEGREES);
            mTest8.sideSideAngle(12.4, 7.6, 125);
        }

        return rootView;
    }

    /**
     * @param view
     *         The View returned by {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}.
     * @param savedInstanceState
     *         If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
