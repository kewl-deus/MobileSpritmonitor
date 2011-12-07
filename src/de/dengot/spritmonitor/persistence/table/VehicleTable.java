package de.dengot.spritmonitor.persistence.table;


import de.dengot.spritmonitor.persistence.metadata.DbTable;

import static de.dengot.spritmonitor.persistence.metadata.DbColumn.column;
import static de.dengot.spritmonitor.persistence.metadata.DbTable.primaryKey;
import static de.dengot.spritmonitor.persistence.metadata.DbTable.table;

public final class VehicleTable {

    public static final String ID = "_id";
    public static final String NAME = "name";


    public static final DbTable TABLE = table("vehicle",
            primaryKey(column(ID, "integer")),
            column(NAME, "text")
    );

    private VehicleTable(){}

    public static String name(){
        return TABLE.getName();
    }
}
