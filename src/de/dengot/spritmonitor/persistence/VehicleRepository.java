package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class VehicleRepository {

    private final DbHelper dbHelper;

    public VehicleRepository(Context context){
        this.dbHelper = new DbHelper(context);
    }

    public void close(){
        this.dbHelper.close();
    }

    public Cursor getVehicles(){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        return db.query("vehicle", null, null, null, null, null, "name asc");
    }

}
