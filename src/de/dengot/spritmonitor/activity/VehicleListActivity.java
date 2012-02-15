package de.dengot.spritmonitor.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.*;
import android.widget.ListView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.fragment.VehicleListFragment;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.widget.VehicleCursorAdapter;
import de.dengot.spritmonitor.persistence.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class VehicleListActivity extends FragmentActivity {


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
