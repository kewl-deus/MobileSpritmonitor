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
import de.dengot.spritmonitor.persistence.mapper.VehicleRowMapper;
import de.dengot.spritmonitor.persistence.table.VehicleTable;

import java.text.MessageFormat;

public class VehicleCursorAdapter extends CursorAdapter {

    private VehicleRowMapper rowMapper;

    private int layout;


    public VehicleCursorAdapter(Context context, int layout, Cursor cursor) {
        super(context, cursor, false);
        this.layout = layout;
        this.rowMapper = new VehicleRowMapper();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);

        ViewHolder row = new ViewHolder();
        row.name = (TextView) view.findViewById(R.id.text_vehicle_name);
        row.summary = (TextView) view.findViewById(R.id.text_vehicle_summary);

        fillRow(row, cursor);
        view.setTag(row);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder rowData = (ViewHolder) view.getTag();
        fillRow(rowData, cursor);
    }


    private void fillRow(ViewHolder viewHolder, Cursor cursor){
        Vehicle vehicle = rowMapper.mapRow(cursor);
        viewHolder.name.setText(vehicle.getName());
        String summaryMsg = MessageFormat.format("Ø {0} l/100km, Tachostand: {1} km", vehicle.getAverageConsumption(), vehicle.getOdometer());
        viewHolder.summary.setText(summaryMsg);
    }

    private class ViewHolder {
        TextView name;
        TextView summary;
    }

}

