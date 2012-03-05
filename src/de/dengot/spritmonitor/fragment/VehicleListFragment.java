package de.dengot.spritmonitor.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.*;
import android.widget.ListView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.activity.EditVehicleActivity;
import de.dengot.spritmonitor.activity.FuelingListActivity;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.persistence.loader.VehicleCursorLoader;
import de.dengot.spritmonitor.widget.VehicleCursorAdapter;


public class VehicleListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = VehicleListFragment.class.getSimpleName();

    private static final int LOADER_ID = (TAG + "." + VehicleCursorLoader.TAG).hashCode();

    private VehicleCursorAdapter cursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        final Bundle noLoaderArgs = null;
        getLoaderManager().initLoader(LOADER_ID, noLoaderArgs, this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v(TAG, "onActivityCreated");
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

        //setContentView(R.layout.vehiclelist);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(TAG, "onListItemClick");
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), FuelingListActivity.class);
        intent.putExtra(FuelingListActivity.PARAM_VECHILE_ID, id);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Log.v(TAG, "onCreateOptionsMenu");
        menuInflater.inflate(R.menu.vehiclelist_optionsmenu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.new_vehicle:
                Intent newVehicleIntent = new Intent(getActivity(), EditVehicleActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.v(TAG, "onCreateContextMenu");
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.vehiclelist_contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.v(TAG, "onContextItemSelected");
        switch (item.getItemId()) {
            case R.id.edit_vehicle:
                Intent editVehicleIntent = new Intent(getActivity(), EditVehicleActivity.class);
                Vehicle vehicle = (Vehicle) getListView().getSelectedItem();
                editVehicleIntent.putExtra(EditVehicleActivity.VEHICLE_ID, vehicle.getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    //------------------------ LoaderCallbacks -----------------------------------------------

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.v(TAG, "onCreateLoader: " + id);
        return new VehicleCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor data) {
        Log.v(TAG, "onLoadFinished");
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
        Log.v(TAG, "onLoaderReset");
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        this.cursorAdapter.swapCursor(null);
    }

}
