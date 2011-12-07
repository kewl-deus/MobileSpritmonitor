package de.dengot.spritmonitor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.VehicleRepository;


public class EditVehicleActivity extends Activity {

    public static final String VEHICLE_ID = "VEHICLE_ID";

    private VehicleRepository repo;
    private Vehicle vehicle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repo = new VehicleRepository(this);

        setContentView(R.layout.vehicleform);

        Button saveButton = (Button) findViewById(R.id.button_save_vehicle);
        saveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                saveVehicle();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(VEHICLE_ID)){
            long vid = extras.getLong(VEHICLE_ID);
            vehicle = repo.findByKey(vid);
            updateViewFromModel();
        } else {
            vehicle = new VehicleBean();
        }
    }

    private void updateViewFromModel() {
        EditText nameInput = (EditText) findViewById(R.id.input_vehicle_name);
        nameInput.setText(vehicle.getName());
    }

    private void updateModelFromView() {
        EditText nameInput = (EditText) findViewById(R.id.input_vehicle_name);
        vehicle.setName(nameInput.getText().toString());
    }

    private void saveVehicle() {
        updateModelFromView();
        repo.save(vehicle);
        Toast.makeText(this, "Fahrzeug gespeichert", Toast.LENGTH_LONG);
    }
}