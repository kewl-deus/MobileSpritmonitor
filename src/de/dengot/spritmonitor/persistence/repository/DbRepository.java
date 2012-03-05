package de.dengot.spritmonitor.persistence.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import de.dengot.spritmonitor.model.Identifyable;
import de.dengot.spritmonitor.persistence.DbHelper;
import de.dengot.spritmonitor.persistence.metadata.DbTable;

public abstract class DbRepository<E extends Identifyable> {

    protected final DbHelper dbHelper;
    protected final DbTable dbTable;

    public DbRepository(Context context, DbTable dbTable) {
        this(new DbHelper(context), dbTable);
    }

    public DbRepository(DbHelper dbHelper, DbTable dbTable) {
        this.dbHelper = dbHelper;
        this.dbTable = dbTable;
    }

    public void close() {
        this.dbHelper.close();
    }

    public Cursor findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(dbTable.getName(), null, null, null, null, null, null);
    }

    public E findByKey(long id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbTable.getName(), null, dbTable.getPrimaryKey()[0] + " = ?", toStringArray(id), null, null, null);
        E entity = mapRow(cursor);
        cursor.close();
        return entity;
    }


    public void save(E entity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(dbTable.getName(), null, dbTable.getPrimaryKey()[0] + " = ?", toStringArray(entity.getId()), null, null, null);
        int count = cursor.getCount();
        cursor.close();

        if (count > 0) {
            updateEntity(db, entity);
        } else {
            insertEntity(db, entity);
        }
        db.close();
    }

    public abstract E mapRow(Cursor cursor);

    protected abstract void insertEntity(SQLiteDatabase db, E entity);

    protected abstract void updateEntity(SQLiteDatabase db, E entity);


    protected String[] toStringArray(Object... args) {
        String[] strings = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            strings[i] = String.valueOf(args[i]);
        }
        return strings;
    }
}
