package de.dengot.spritmonitor.persistence.mapper;


import android.content.ContentValues;
import android.database.Cursor;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

public class VehicleRowMapper implements RowMapper<Vehicle> {

    private ColumnIndexHolder columnIndex = null;

    @Override
    public Vehicle mapRow(Cursor cursor) {
        if (columnIndex == null || ! columnIndex.matches(cursor)){
            columnIndex = new ColumnIndexHolder(cursor);
        }
        VehicleBean v = new VehicleBean();
        return v;
    }

    @Override
    public ContentValues extractValues(Vehicle v) {
        ContentValues cv = new ContentValues();
        cv.put(VehicleTable.NAME, v.getName());
        return cv;
    }


    private class ColumnIndexHolder {
        private final int cursorHash;

        final int NAME;

        public ColumnIndexHolder(Cursor cursor){
            cursorHash = cursor.hashCode();
            NAME = cursor.getColumnIndexOrThrow(VehicleTable.NAME);
        }

        boolean matches(Cursor cursor){
            return this.cursorHash == cursor.hashCode();
        }
    }
}
