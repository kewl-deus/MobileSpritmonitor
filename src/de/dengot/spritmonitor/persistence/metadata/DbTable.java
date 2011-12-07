package de.dengot.spritmonitor.persistence.metadata;


import java.text.MessageFormat;
import java.util.*;

public class DbTable {

    private String name;

    private DbColumn[] columns;

    private DbColumn[] primaryKey;

    public static DbTable table(String name, DbColumn... columns) {
        return new DbTable(name, columns);
    }

    public static DbTable table(String name, List<DbColumn> primaryKeyColumns, DbColumn... columns) {
        List<DbColumn> allCols = new ArrayList<DbColumn>();
        allCols.addAll(primaryKeyColumns);
        allCols.addAll(Arrays.asList(columns));

        DbColumn[] colArray = new DbColumn[allCols.size()];
        allCols.toArray(colArray);

        DbTable tab = new DbTable(name, colArray);
        tab.primaryKey = new DbColumn[primaryKeyColumns.size()];
        primaryKeyColumns.toArray(tab.primaryKey);
        return tab;
    }

    public static List<DbColumn> primaryKey(DbColumn... columns) {
        return Arrays.asList(columns);
    }


    public DbTable(String name, DbColumn... columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public DbColumn[] getColumns() {
        return columns;
    }

    public int indexOf(String columnName) {
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].getName().equals(columnName)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public DbColumn getColumn(String columnName) {
        for (DbColumn column : columns) {
            if (column.getName().equals(columnName)) {
                return column;
            }
        }
        throw new NoSuchElementException();
    }


    public void setPrimaryKey(String... columnNames) {
        DbColumn[] keyColumns = new DbColumn[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            keyColumns[i] = getColumn(columnNames[i]);
        }
        primaryKey = keyColumns;
    }

    public DbColumn[] getPrimaryKey(){
        return primaryKey;
    }

    public String toDDL() {
        StringBuilder columnDDL = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            columnDDL.append(columns[i].toDDL());
            if (i < columns.length - 1) {
                columnDDL.append(" , ");
            }
        }

        StringBuilder pkDDL = new StringBuilder();
         if (primaryKey != null && primaryKey.length > 0) {
            pkDDL.append(" , primary key (");
            for (int i = 0; i < primaryKey.length; i++) {
                pkDDL.append(primaryKey[i].getName());
                if (i < primaryKey.length - 1) {
                    pkDDL.append(",");
                }
            }
             pkDDL.append(")");
        }
        
        String ddl = MessageFormat.format("create table {0} ( {1} {2} )", getName(), columnDDL, pkDDL);

        return ddl;
    }
}
