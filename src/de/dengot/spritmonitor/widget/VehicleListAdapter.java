package de.dengot.spritmonitor.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Vehicle;

import java.text.MessageFormat;
import java.util.List;

public class VehicleListAdapter extends ArrayAdapter<Vehicle> {

    private LayoutInflater layoutInflater;
    private int itemLayoutResourceId;

    public VehicleListAdapter(Context context, int itemLayoutResourceId, List<Vehicle> objects) {
        super(context, itemLayoutResourceId, -1, objects);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayoutResourceId = itemLayoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null) {
            view = layoutInflater.inflate(itemLayoutResourceId, parent, false);
        } else {
            view = convertView;
        }


        Vehicle vehicle = getItem(position);

        TextView nameView = (TextView) view.findViewById(R.id.text_vehicle_name);
        TextView summaryView = (TextView) view.findViewById(R.id.text_vehicle_summary);

        nameView.setText(vehicle.getName());
        String summaryMsg = MessageFormat.format("Ø {0} l/100km, Tachostand: {1} km", vehicle.getAverageConsumption(), vehicle.getOdometer());
        summaryView.setText(summaryMsg);

        return view;
    }
}
