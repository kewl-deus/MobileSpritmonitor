package de.dengot.spritmonitor.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.fragment.VehicleListFragment;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.widget.VehicleCursorAdapter;
import de.dengot.spritmonitor.persistence.VehicleRepository;
import de.dengot.spritmonitor.widget.VehicleListAdapter;

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
            //ArrayListFragment listFragment = new ArrayListFragment();
            fm.beginTransaction().add(android.R.id.content, listFragment).commit();
        }

    }

    public static class ArrayListFragment extends ListFragment {

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


            List<Vehicle> vehicleList = new ArrayList<Vehicle>();
            vehicleList.add(new VehicleBean("R32"));
            vehicleList.add(new VehicleBean("GTI"));
            vehicleList.add(new VehicleBean("Street Triple"));

            VehicleListAdapter vehiclesAdapter = new VehicleListAdapter(getActivity(), R.layout.vehiclelist_item, vehicleList);
            setListAdapter(vehiclesAdapter);

        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }

}
