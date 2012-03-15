package de.dengot.spritmonitor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.dengot.spritmonitor.R;
import de.dengot.spritmonitor.model.Vehicle;
import de.dengot.spritmonitor.model.VehicleBean;
import de.dengot.spritmonitor.persistence.loader.EntityLoader;
import de.dengot.spritmonitor.persistence.repository.VehicleRepository;

public class VehicleDetailFragment extends BasicFormFragment implements LoaderManager.LoaderCallbacks<Vehicle> {

    private static final String TAG = VehicleDetailFragment.class.getSimpleName();
    private static final int LOADER_ID = (TAG + ".EntityLoader").hashCode();


    private long vehicleId;
    private Vehicle vehicle;

    public VehicleDetailFragment(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicledetail, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle noLoaderArgs = null;
        getLoaderManager().initLoader(LOADER_ID, noLoaderArgs, this);
    }

    private void updateViewFromModel() {
        TextView nameText = findView(R.id.text_vehicledetail_name);
        nameText.setText(vehicle.getName());
    }

    //------------------------ LoaderCallbacks -----------------------------------------------

    @Override
    public Loader<Vehicle> onCreateLoader(int id, Bundle args) {
        return new EntityLoader<Vehicle>(getActivity(), new VehicleRepository(getActivity()), this.getId());
    }

    @Override
    public void onLoadFinished(Loader<Vehicle> vehicleLoader, Vehicle data) {
        this.vehicle = data;
        updateViewFromModel();
    }

    @Override
    public void onLoaderReset(Loader<Vehicle> vehicleLoader) {
        this.vehicle = new VehicleBean();
        updateViewFromModel();
    }
}
