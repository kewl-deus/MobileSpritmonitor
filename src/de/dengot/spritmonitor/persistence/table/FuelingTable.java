package de.dengot.spritmonitor.persistence.table;


import de.dengot.spritmonitor.persistence.metadata.DbTable;

import static de.dengot.spritmonitor.persistence.metadata.DbColumn.column;
import static de.dengot.spritmonitor.persistence.metadata.DbTable.primaryKey;
import static de.dengot.spritmonitor.persistence.metadata.DbTable.table;

public final class FuelingTable {

    public static final String ID = "_id";
    public static final String VEHICLE_ID = "vehicle_id";
    public static final String FILLDATE = "filldate";
    public static final String ODOMETER = "odometer";
    public static final String DISTANCE = "distance";
    public static final String QUANTITY = "quantity";
    public static final String COST = "cost";
    public static final String FILLUP = "fillup";

    public static DbTable TABLE = table("fueling",
            primaryKey(column(ID, "integer")),
            column(VEHICLE_ID, "integer"),
            column(FILLDATE, "integer"),
            column(ODOMETER, "integer"),
            column(DISTANCE, "integer"),
            column(QUANTITY, "real"),
            column(COST, "real"),
            column(FILLUP, "integer")
    );

    private FuelingTable() {
    }

    public static String name() {
        return TABLE.getName();
    }
}
