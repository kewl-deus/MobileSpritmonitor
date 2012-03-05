package de.dengot.spritmonitor.widget;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.persistence.mapper.FuelingRowMapper;

import java.text.MessageFormat;

public class FuelingCursorAdapter extends CursorAdapter {

    private FuelingRowMapper rowMapper;
    private int layout;

    public FuelingCursorAdapter(Context context, int layout, Cursor cursor) {
        super(context, cursor, false);
        this.layout = layout;
        this.rowMapper = new FuelingRowMapper();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        
        ViewHolder vh = new ViewHolder();
        vh.filldate = (TextView) view.findViewById(R.id.text_fuelitem_filldate);
        vh.consumption = (TextView) view.findViewById(R.id.text_fuelitem_consumption);
        vh.unitprice  = (TextView) view.findViewById(R.id.text_fuelitem_unitprice);
        vh.distance = (TextView) view.findViewById(R.id.text_fuelitem_distance);
        vh.quantity = (TextView) view.findViewById(R.id.text_fuelitem_quantity);
        vh.cost = (TextView) view.findViewById(R.id.text_fuelitem_cost);
        
        fillRow(vh, cursor);
        view.setTag(vh);
        
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder rowData = (ViewHolder) view.getTag();
        fillRow(rowData, cursor);
    }
    
    private void fillRow(ViewHolder vh, Cursor cursor){
        Fueling fuel = rowMapper.mapRow(cursor);
        setText(vh.filldate, "{0,date,dd.MM.yy}", fuel.getFilldate());
        setText(vh.consumption, "Ø {0,number,#.##} l", fuel.getConsumption());
        setText(vh.unitprice, "Preis: {0,number,#.##} EUR", fuel.getUnitPrice());
        setText(vh.distance, "Trip: {0,number,integer} km", fuel.getDistance());
        setText(vh.quantity, "Verbrauch: {0,number,#.##} l", fuel.getQuantity());
        setText(vh.cost, "Kosten: {0,number,#.##} EUR", fuel.getCost());
    }

    private void setText(TextView textView, String message, Object...args){
        String txt = MessageFormat.format(message, args);
        textView.setText(txt);
    }
    
    private class ViewHolder {
        TextView filldate;
        TextView consumption;
        TextView unitprice;
        TextView distance;
        TextView quantity;
        TextView cost;
    }

}
