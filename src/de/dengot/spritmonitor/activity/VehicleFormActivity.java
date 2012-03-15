package de.dengot.spritmonitor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import de.dengot.spritmonitor.fragment.VehicleFormFragment;


public class VehicleFormActivity extends FragmentActivity {

    public static final String PARAM_VEHICLE_ID = "vehicleId";

    private static final String TAG = VehicleFormActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        //delegate to fragment
        FragmentManager fm = getSupportFragmentManager();

        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {

            long vehicleId = getIntent().getLongExtra(PARAM_VEHICLE_ID, 0);
            Log.v(TAG, "Param(vehicleId) = " + vehicleId);

            VehicleFormFragment formFragment = new VehicleFormFragment(vehicleId);

            fm.beginTransaction().add(android.R.id.content, formFragment).commit();
        }

    }
}