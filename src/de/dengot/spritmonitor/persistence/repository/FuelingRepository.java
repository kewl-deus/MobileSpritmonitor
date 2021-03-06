package de.dengot.spritmonitor.persistence.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.FuelingBean;
import de.dengot.spritmonitor.persistence.DbHelper;
import de.dengot.spritmonitor.persistence.mapper.FuelingRowMapper;
import de.dengot.spritmonitor.persistence.table.FuelingTable;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

public class FuelingRepository extends DbRepository<Fueling> {

    private FuelingRowMapper rowMapper;

    public FuelingRepository(Context context) {
        super(context, FuelingTable.TABLE);
        rowMapper = new FuelingRowMapper();
    }

    public FuelingRepository(DbHelper dbHelper) {
        super(dbHelper, FuelingTable.TABLE);
        rowMapper = new FuelingRowMapper();
    }

    public Cursor findByVehicle(long vehicleId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(dbTable.getName(), null, FuelingTable.VEHICLE_ID + "=?", toStringArray(vehicleId), null, null, null);
    }

    @Override
    protected void insertEntity(SQLiteDatabase db, Fueling fueling) {
        ContentValues values = new ContentValues();
        //TODO values.put(FuelingTable.PARAM_VEHICLE_ID, fueling);

        long id = db.insert(dbTable.getName(), null, values);
        fueling.setId(id);
    }

    @Override
    protected void updateEntity(SQLiteDatabase db, Fueling fueling) {
    }

    @Override
    public Fueling mapRow(Cursor cursor) {
        return rowMapper.mapRow(cursor);
    }
}
