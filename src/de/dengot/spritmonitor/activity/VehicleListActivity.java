package de.dengot.spritmonitor.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.widget.VehicleCursorAdapter;
import de.dengot.spritmonitor.persistence.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class VehicleListActivity extends ListActivity {


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vehiclelist);

        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        vehicleList.add(new VehicleBean("R32"));
        vehicleList.add(new VehicleBean("GTI"));
        vehicleList.add(new VehicleBean("Street Triple"));

        //VehicleListAdapter vehiclesAdapter = new VehicleListAdapter(this, R.layout.vehiclelist_item, vehicleList);
        //setListAdapter(vehiclesAdapter);

        VehicleRepository repo = new VehicleRepository(this);
        VehicleCursorAdapter cursorAdapter = new VehicleCursorAdapter(this, R.layout.vehiclelist_item, repo.findAll());
        setListAdapter(cursorAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object item = l.getItemAtPosition(position);
        if (item instanceof VehicleBean) {
            Vehicle vehicle = (Vehicle) l.getItemAtPosition(position);
            Intent detailIntent = new Intent(this, FuelingListActivity.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.vehiclelist_optionsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_vehicle:
                Intent newVehicleIntent = new Intent(this, EditVehicleActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.vehiclelist_contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit_vehicle:
                Intent editVehicleIntent = new Intent(this, EditVehicleActivity.class);
                Vehicle vehicle = (Vehicle) getListView().getSelectedItem();
                editVehicleIntent.putExtra(EditVehicleActivity.VEHICLE_ID, vehicle.getId());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
