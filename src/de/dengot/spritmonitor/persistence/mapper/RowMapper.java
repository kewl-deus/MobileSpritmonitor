package de.dengot.spritmonitor.persistence.mapper;

import android.content.ContentValues;
import android.database.Cursor;

public interface RowMapper<E> {
    E mapRow(Cursor cursor);
    
    ContentValues extractValues(E entity);
}
