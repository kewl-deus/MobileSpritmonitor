package de.dengot.spritmonitor.persistence.mapper;

import android.database.Cursor;

public interface RowMapper<E> {
    E mapRow(Cursor cursor);
}
