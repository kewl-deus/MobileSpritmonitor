package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.dengot.spritmonitor.persistence.table.FuelingTable;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Random;

import static java.util.Calendar.*;

import static de.dengot.spritmonitor.persistence.metadata.DbTable.table;

public class DbHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "spritmonitor.db";
    static final int DB_VERSION = 1;

    /**
     * Generate sequential upcounting db version number
     * for debugging (to always run update)
     *
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


    private void createTables(SQLiteDatabase db) {
        final String vehicleDDL = VehicleTable.TABLE.toDDL();
        Log.v("Creating vehicle table", vehicleDDL);
        db.execSQL(vehicleDDL);

        final String fuelingDDL = FuelingTable.TABLE.toDDL();
        Log.v("Creating fueling table", fuelingDDL);
        db.execSQL(fuelingDDL);

        //TODO: remove testdata insert
        insertTestData(db);
    }


    private void dropTables(SQLiteDatabase db) {
        final String[] tabs = {VehicleTable.TABLE.getName(), FuelingTable.TABLE.getName()};
        for (String tab : tabs) {
            String dropStmt = "drop table if exists " + tab;
            db.execSQL(dropStmt);
        }
    }

    private void insertTestData(SQLiteDatabase db) {

        db.execSQL("insert into vehicle(_id, name) values(1, 'R32 Test')");
        db.execSQL("insert into vehicle(_id, name) values(2, 'GTI Test')");
        db.execSQL("insert into vehicle(_id, name) values(3, 'Street 3ple Test')");


        for (int i = 1; i <= 20; i++) {
            for (int v = 1; v <= 3; v++) {
//                String sql = MessageFormat.format("insert into fueling (_id, vehicle_id, filldate, odometer, distance, quantity, cost) values ({0}, {1}, {2}, {3}, {4}, {5}, {6})",
//                        i + v * 100, v, System.currentTimeMillis() - i * 86400 * 1000, i * 500, 500 + i, 40.42 + i, 50.67 + i);
                db.execSQL("insert into fueling (_id, vehicle_id, filldate, odometer, distance, quantity, cost) values (?, ?, ?, ?, ?, ?, ?)",
                        new Object[]{i + v * 100, v, System.currentTimeMillis() - i * 86400 * 1000, i * 500, 500 + i, 40.42 + i, 50.67 + i});
            }
        }

    }
}