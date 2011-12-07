package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.FuelingBean;
import de.dengot.spritmonitor.persistence.table.FuelingTable;

public class FuelingRepository extends DbRepository<Fueling> {

    public FuelingRepository(Context context) {
        super(context, FuelingTable.TABLE);
    }

    public FuelingRepository(DbHelper dbHelper) {
        super(dbHelper, FuelingTable.TABLE);
    }

    @Override
    protected void insertEntity(SQLiteDatabase db, Fueling entity) {
    }

    @Override
    protected void updateEntity(SQLiteDatabase db, Fueling entity) {
    }

    @Override
    protected Fueling mapRow(Cursor cursor) {
        Fueling f = new FuelingBean();
        return f;
    }
}
