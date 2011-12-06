package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        db.execSQL(VehicleTable.TABLE.toDDL());
        db.execSQL(FuelingTable.TABLE.toDDL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String[] tabs = {VehicleTable.TABLE.getName(), FuelingTable.TABLE.getName()};
        for (String tab : tabs) {
            String dropStmt = "drop table if exists " + tab;
            db.execSQL(dropStmt);
        }
        onCreate(db);
    }
}