package de.dengot.spritmonitor.persistence;

import android.database.Cursor;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import java.util.List;

public class VehicleCursor implements Vehicle {

    private Cursor cursor;

    public VehicleCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public void addFueling(Fueling fueling) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Fueling> getFuelings() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getName() {
        return cursor.getString(VehicleTable.TABLE.indexOf(VehicleTable.NAME));
    }

    public void setName(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
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
