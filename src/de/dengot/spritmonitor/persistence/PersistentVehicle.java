package de.dengot.spritmonitor.persistence;

import android.database.Cursor;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import java.util.List;

public class PersistentVehicle extends VehicleBean implements Vehicle {

    private FuelingRepository fuelingRepo;

    public PersistentVehicle(FuelingRepository fuelingRepo) {
        this.fuelingRepo = fuelingRepo;
    }

    public int getOdometer() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getTotalDistance() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public float getTotalQuantity() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public float getTotalCosts() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public float getAverageConsumption() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public float getAverageUnitPrice() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
