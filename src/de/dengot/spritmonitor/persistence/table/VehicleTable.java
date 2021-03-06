package de.dengot.spritmonitor.persistence.table;


import de.dengot.spritmonitor.persistence.metadata.DbTable;

import static de.dengot.spritmonitor.persistence.metadata.DbColumn.column;
import static de.dengot.spritmonitor.persistence.metadata.DbTable.primaryKey;
import static de.dengot.spritmonitor.persistence.metadata.DbTable.table;

public final class VehicleTable {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String EXTERNAL_ID = "external_id";
    public static final String PRODUCTION_YEAR = "prod_year";
    public static final String BHP = "bhp";
    public static final String CCM = "ccm";



    public static final DbTable TABLE = table("vehicle",
            primaryKey(column(ID, "integer")),
            column(NAME, "text"),
            column(EXTERNAL_ID, "text")
    );

    private VehicleTable(){}

    public static String name(){
        return TABLE.getName();
    }
}
