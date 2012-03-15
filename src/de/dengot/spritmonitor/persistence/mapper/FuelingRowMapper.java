package de.dengot.spritmonitor.persistence.mapper;


import android.content.ContentValues;
import android.database.Cursor;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.FuelingBean;
import de.dengot.spritmonitor.persistence.table.FuelingTable;

import java.util.Date;

public class FuelingRowMapper implements RowMapper<Fueling> {

    private ColumnIndexHolder columnIndex = null;
    
    @Override
    public Fueling mapRow(Cursor cursor) {
        if (columnIndex == null || ! columnIndex.matches(cursor)){
            columnIndex = new ColumnIndexHolder(cursor);
        }
        Fueling f = new FuelingBean();
        f.setId(cursor.getLong(columnIndex.ID));
        f.setCost(cursor.getFloat(columnIndex.COST));
        f.setDistance(cursor.getInt(columnIndex.DISTANCE));
        f.setFilldate(new Date(cursor.getLong(columnIndex.FILLDATE)));
        f.setOdometer(cursor.getInt(columnIndex.ODOMETER));
        f.setQuantity(cursor.getFloat(columnIndex.QUANTITY));
        f.setFillup(cursor.getInt(columnIndex.FILLUP) != 0);
        return f;
    }

    @Override
    public ContentValues extractValues(Fueling f) {
        ContentValues cv = new ContentValues();
        cv.put(FuelingTable.VEHICLE_ID, f.getVehicleId());
        cv.put(FuelingTable.FILLUP, f.isFillup() ? 1 : 0);
        cv.put(FuelingTable.COST, f.getCost());
        cv.put(FuelingTable.DISTANCE, f.getDistance());
        cv.put(FuelingTable.FILLDATE, f.getFilldate().getTime());
        cv.put(FuelingTable.ODOMETER, f.getOdometer());
        cv.put(FuelingTable.QUANTITY, f.getQuantity());
        return cv;
    }


    private class ColumnIndexHolder {
        
        private final int cursorHash;
        
        final int ID;
        final int FILLDATE;
        final int DISTANCE;
        final int QUANTITY;
        final int COST;
        final int ODOMETER;
        final int FILLUP;

        public ColumnIndexHolder(Cursor cursor){
            cursorHash = cursor.hashCode();

            ID = cursor.getColumnIndexOrThrow(FuelingTable.ID);
            FILLDATE = cursor.getColumnIndexOrThrow(FuelingTable.FILLDATE);
            DISTANCE = cursor.getColumnIndexOrThrow(FuelingTable.DISTANCE);
            QUANTITY = cursor.getColumnIndexOrThrow(FuelingTable.QUANTITY);
            COST = cursor.getColumnIndexOrThrow(FuelingTable.COST);
            ODOMETER = cursor.getColumnIndexOrThrow(FuelingTable.ODOMETER);
            FILLUP = cursor.getColumnIndexOrThrow(FuelingTable.FILLUP);
        }
        
        boolean matches(Cursor cursor){
            return this.cursorHash == cursor.hashCode();
        }
    }
}
