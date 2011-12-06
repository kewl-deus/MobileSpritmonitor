package de.dengot.spritmonitor.persistence;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import de.dengot.spritmonitor.R;

public class MyCursorAdapter extends CursorAdapter {
    private static final String TAG = "MyCursorAdapter";
    private final int NAME_COLUMN;
    private final int ADDRESS_COLUMN;

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);
        NAME_COLUMN = c.getColumnIndexOrThrow("name");
        ADDRESS_COLUMN = c.getColumnIndexOrThrow("address");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row, null);

        MyRowViewHolder rowData = new MyRowViewHolder();

        rowData.name = (TextView) view.findViewById(-1); //R.id.name);
        rowData.address = (TextView) view.findViewById(-1); //R.id.address);

        rowData.name.setText(cursor.getString(NAME_COLUMN));
        rowData.address.setText(cursor.getString(ADDRESS_COLUMN));

        view.setTag(rowData);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyRowViewHolder rowData = (MyRowViewHolder) view.getTag();
        rowData.name.setText(cursor.getString(NAME_COLUMN));
        rowData.address.setText(cursor.getString(ADDRESS_COLUMN));
    }

    public static class MyRowViewHolder {
        TextView name;
        TextView address;
    }
}

