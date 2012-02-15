package de.dengot.spritmonitor.widget;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import java.text.MessageFormat;

public class VehicleCursorAdapter extends CursorAdapter {

    private final int NAME_COLUMN;

    private int layout;

    public VehicleCursorAdapter(Context context, int layout, Cursor c) {
        super(context, c);
        this.layout = layout;
        NAME_COLUMN = c.getColumnIndexOrThrow(VehicleTable.NAME);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);

        VehicleRow row = new VehicleRow();
        row.name = (TextView) view.findViewById(R.id.text_vehicle_name);
        row.summary = (TextView) view.findViewById(R.id.text_vehicle_summary);

        fillRow(row, cursor);
        view.setTag(row);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        VehicleRow rowData = (VehicleRow) view.getTag();
        fillRow(rowData, cursor);
    }


    private void fillRow(VehicleRow row, Cursor cursor){
        Vehicle vehicle = new VehicleBean(cursor.getString(NAME_COLUMN));

        row.name.setText(vehicle.getName());

        String summaryMsg = MessageFormat.format("Ø {0} l/100km, Tachostand: {1} km", vehicle.getAverageConsumption(), vehicle.getOdometer());
        row.summary.setText(summaryMsg);
    }

    /**
     * ViewHolder
     */
    public static class VehicleRow {
        TextView name;
        TextView summary;
    }
}

