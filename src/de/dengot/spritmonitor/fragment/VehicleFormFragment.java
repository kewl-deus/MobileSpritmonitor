package de.dengot.spritmonitor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Vehicle;

public class VehicleFormFragment extends BasicFormFragment {

    private long vehicleId;
    private Vehicle vehicle;

    public VehicleFormFragment(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicleform, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button saveButton = findView(R.id.button_save_vehicle);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveVehicle();
            }
        });
    }


    private void updateViewFromModel() {
        EditText nameInput = findView(R.id.input_vehicle_name);
        EditText extidInput = findView(R.id.input_vehicle_extid);
        nameInput.setText(vehicle.getName());
        extidInput.setText(vehicle.getExternalId());
    }

    private void updateModelFromView() {
        EditText nameInput = findView(R.id.input_vehicle_name);
        EditText extidInput = findView(R.id.input_vehicle_extid);
        vehicle.setName(nameInput.getText().toString());
        vehicle.setExternalId(extidInput.getText().toString());
    }

    private void saveVehicle() {
        updateModelFromView();
        //TODO repo.save(vehicle);
        Toast.makeText(getActivity(), "Fahrzeug gespeichert", Toast.LENGTH_LONG);
        //TODO close View
    }
}
