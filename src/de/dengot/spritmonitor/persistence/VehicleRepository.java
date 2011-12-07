package de.dengot.spritmonitor.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import de.dengot.spritmonitor.model.Identifyable;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.metadata.DbTable;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

public class VehicleRepository extends DbRepository<Vehicle> {

    public VehicleRepository(Context context) {
        super(context, VehicleTable.TABLE);
    }

    public VehicleRepository(DbHelper dbHelper) {
        super(dbHelper, VehicleTable.TABLE);
    }

    @Override
    public Cursor findAll() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        return db.query(dbTable.getName(), null, null, null, null, null, VehicleTable.NAME);
    }


    @Override
    protected Vehicle mapRow(Cursor cursor) {
        VehicleBean v = new VehicleBean();
        v.setId(cursor.getLong(dbTable.indexOf(VehicleTable.ID)));
        v.setName(cursor.getString(dbTable.indexOf(VehicleTable.NAME)));

        return v;
    }

    @Override
    protected void insertEntity(SQLiteDatabase db, Vehicle vehicle) {
        ContentValues values = new ContentValues();
        values.put(VehicleTable.NAME, vehicle.getName());
        long id = db.insert(dbTable.getName(), null, values);
        vehicle.setId(id);
    }

    @Override
    protected void updateEntity(SQLiteDatabase db, Vehicle vehicle) {
        ContentValues values = new ContentValues();
        values.put(VehicleTable.NAME, vehicle.getName());
        db.update(dbTable.getName(), values, VehicleTable.ID + " = ?", toStringArray(vehicle.getId()));
    }

}
