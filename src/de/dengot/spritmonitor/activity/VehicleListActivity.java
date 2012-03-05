package de.dengot.spritmonitor.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import de.dengot.spritmonitor.fragment.VehicleListFragment;

public class VehicleListActivity extends FragmentActivity {

    private static final String TAG = VehicleListActivity.class.getSimpleName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        //delegate to fragment
        FragmentManager fm = getSupportFragmentManager();

        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
            VehicleListFragment listFragment = new VehicleListFragment();
            fm.beginTransaction().add(android.R.id.content, listFragment).commit();
        }

    }

}
