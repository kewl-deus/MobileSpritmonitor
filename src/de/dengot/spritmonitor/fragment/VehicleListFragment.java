package de.dengot.spritmonitor.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.*;
import android.widget.ListView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.activity.EditVehicleActivity;
import de.dengot.spritmonitor.activity.FuelingListActivity;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.VehicleRepository;
import de.dengot.spritmonitor.persistence.loader.VehicleCursorLoader;
import de.dengot.spritmonitor.widget.VehicleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class VehicleListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private VehicleCursorAdapter cursorAdapter;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Give some text to display if there is no data.  In a real
        // application this would come from a resource.
        setEmptyText("No vehicles");

        // We have a menu item to show in action bar.
        setHasOptionsMenu(true);


        // Create an empty adapter we will use to display the loaded data.
        final Cursor noCursor = null;
        cursorAdapter = new VehicleCursorAdapter(getActivity(), R.layout.vehiclelist_item, noCursor);
        setListAdapter(cursorAdapter);
        

        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        final Bundle noLoaderArgs = null;
        getLoaderManager().initLoader(0, noLoaderArgs, this);


        //setContentView(R.layout.vehiclelist);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new VehicleCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor data) {
        this.cursorAdapter.swapCursor(data);
        // The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        this.cursorAdapter.swapCursor(null);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object item = l.getItemAtPosition(position);
        if (item instanceof VehicleBean) {
            Vehicle vehicle = (Vehicle) l.getItemAtPosition(position);
            Intent detailIntent = new Intent(getActivity(), FuelingListActivity.class);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.vehiclelist_optionsmenu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_vehicle:
                Intent newVehicleIntent = new Intent(getActivity(), EditVehicleActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.vehiclelist_contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit_vehicle:
                Intent editVehicleIntent = new Intent(getActivity(), EditVehicleActivity.class);
                Vehicle vehicle = (Vehicle) getListView().getSelectedItem();
                editVehicleIntent.putExtra(EditVehicleActivity.VEHICLE_ID, vehicle.getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

}
