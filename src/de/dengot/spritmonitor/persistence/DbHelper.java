package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.dengot.spritmonitor.persistence.table.FuelingTable;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import static de.dengot.spritmonitor.persistence.metadata.DbTable.table;

class DbHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "spritmonitor.db";
    static final int DB_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        createTables(db);
    }


    private void createTables(SQLiteDatabase db){
        final String vehicleDDL = VehicleTable.TABLE.toDDL();
        Log.v("Creating vehicle table", vehicleDDL);
        db.execSQL(vehicleDDL);

        final String fuelingDDL = FuelingTable.TABLE.toDDL();
        Log.v("Creating fueling table", fuelingDDL);
        db.execSQL(fuelingDDL);
    }



    private void dropTables(SQLiteDatabase db){
        final String[] tabs = {VehicleTable.TABLE.getName(), FuelingTable.TABLE.getName()};
        for (String tab : tabs) {
            String dropStmt = "drop table if exists " + tab;
            db.execSQL(dropStmt);
        }
    }
}