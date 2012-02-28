package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.dengot.spritmonitor.persistence.table.FuelingTable;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import java.util.Calendar;
import static java.util.Calendar.*;

import static de.dengot.spritmonitor.persistence.metadata.DbTable.table;

class DbHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "spritmonitor.db";
    static final int DB_VERSION = 1;

    /**
     * Generate sequential upcounting db version number
     * for debugging (to always run update)
     * @return
     */
    static int generateNewDbVersion() {
        Calendar c = Calendar.getInstance();
        int y = c.get(YEAR) % 2000;
        int d = c.get(DAY_OF_YEAR);
        int secs = c.get(HOUR) * 3600 + c.get(Calendar.MINUTE) * 60 + c.get(SECOND);

        int v = secs + 100000 * d + 10000000 * y;

        return v;
    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, generateNewDbVersion());
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

        //testdata
        db.execSQL("insert into vehicle(_id, name) values(1, 'Vehicle1')");
        db.execSQL("insert into vehicle(_id, name) values(2, 'Vehicle2')");

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